"use strict";

const knex = require("../db/connectDB");
const Hapi = require("@hapi/hapi");
const bcrypt = require("bcryptjs");

const ipv4 = require("../getIpv4");

const init = async () => {
  const server = Hapi.server({
    port: 3000,
    host: ipv4,
    routes: {
      cors: true,
    },
  });
  console.log(ipv4);
  server.route({
    method: "POST",
    path: "/signup",
    handler: async (request, h) => {
      try {
        const { username, password } = request.payload;
        const user = await knex("users")
          .where({ username })
          .count("id as count");
        console.log(user);
        if (user[0].count) {
          console.log(1);
          return h
            .response({
              checkUser: "User already exist in database",
            })
            .code(400);
        }
        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);
        await knex("users").insert({ username, password: hashedPassword });
        const retriveUserInsert = knex("users")
          .select("id", "username")
          .where("username", username);
        return retriveUserInsert;
      } catch (error) {
        console.log(error);
        return { error };
      }
    },
  });

  server.route({
    method: "POST",
    path: "/login",
    handler: async (request, h) => {
      try {
        const { username, password } = request.payload;
        const user = await knex("users")
          .where({ username })
          .count("id as count");
        if (!user[0].count) {
          return h.response({ message: "incorrect username" }).code(400);
        }

        const pwdInDB = await knex
          .select("password")
          .from("users")
          .where("username", username);

        const isMatch = await bcrypt.compare(password, pwdInDB[0].password);

        if (!isMatch) {
          return h.response({ message: "incorrect password" }).code(400);
        }

        return {
          message: "login successfully",
        };
      } catch (error) {
        console.log(error);
        return { error };
      }
    },
  });

  server.route({
    method: "POST",
    path: "/comment/{userId}/{commicId}",
    handler: async (request, h) => {
      try {
        const { comment } = request.payload;
        if (!comment) {
          return h
            .response({
              message: "không được để trống comment",
            })
            .code(400);
        }
        const { userId, commicId } = request.params;
        console.log(comment, commicId);
        await knex("comments").insert({
          content: `${comment}`,
          userId: `${userId}`,
          commicId: `${commicId}`,
        });
        const data = await knex
          .select()
          .table("comments")
          .where("commicId", commicId);
        console.log(data);
        return data;
      } catch (e) {
        console.log(e);
        throw new Error(e);
      }
    },
  });

  server.route({
    method: "POST",
    path: "/manga/save/{userId}/{commicId}",
    handler: async (request, h) => {
      try {
        const { userId, commicId } = request.params;

        const data = await knex.select().table("save_manga").where({
          userId,
          commicId,
        });

        if (data.length) {
          return h
            .response({
              message: `user với id ${userId} đã save bộ truyện này`,
            })
            .code(401);
        }

        await knex("save_manga").insert({ userId, commicId });

        return {
          message: "save truyện thành công",
        };
      } catch (e) {
        console.log(e);
        throw new Error(e);
      }
    },
  });

  server.route({
    method: "GET",
    path: "/manga/get/save/{userId}",
    handler: async (request, h) => {
      try {
        const { userId } = request.params;
        const data = await knex.raw(
          `select userId,commicId,Name,Description,url from save_manga,commics where save_manga.userId=${userId} and save_manga.commicId = commics.id`
        );

        return data[0];
      } catch (e) {
        console.log(e);
        throw new Error(e);
      }
    },
  });

  server.route({
    method: "DELETE",
    path: "/manga/unsave/{userId}/{commicId}",
    handler: async (request, h) => {
      try {
        const { userId, commicId } = request.params;
        const data = await knex("save_manga")
          .where("userId", userId)
          .where("commicId", commicId)
          .del();
        if (!data) {
          return h
            .response({
              message: "truyện đã được bỏ save rồi",
            })
            .code(401);
        }
        return {
          message: "Bỏ save truyện thành công",
        };
      } catch (e) {
        console.log(e);
        throw new Error(e);
      }
    },
  });

  await server.start();
  console.log("Server running on %s", server.info.uri);
};

process.on("unhandledRejection", (err) => {
  console.log(err);
  process.exit(1);
});

init();
