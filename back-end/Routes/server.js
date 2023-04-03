'use strict';

const knex = require('../db/connectDB')
const Hapi = require('@hapi/hapi');
const bcrypt = require('bcryptjs')

const init = async () => {

    const server = Hapi.server({
        port: 3000,
        host: 'localhost'
    });

    server.route({
        method:'POST',
        path:'/signup',
        handler:async (request,h)=>{
            try {
                const {username,password} = request.payload
                const user = await knex('users').where({ username }).count('id as count')
    
                if(user[0].count){
                    return {
                        checkUser : 'User already exist in database'
                    }
                }
                const salt = await bcrypt.genSalt(10);
                const hashedPassword = await bcrypt.hash(password, salt);
                await knex('users').insert({ username , password : hashedPassword })
                const retriveUserInsert = knex('users').select('id','username').where('username',username)
                return retriveUserInsert
            } catch (error) {
                console.log(error)
                return {error}
            }
           
        }
    })

    server.route({
        method:'POST',
        path:'/login',
        handler:async (request,h)=>{
            try {
                const {username , password} = request.payload
                const user = await knex('users').where({ username }).count('id as count')
                if(!user[0].count){
                    return {message: 'incorrect username'}
                }  

                const pwdInDB = await knex.select('password').from('users').where('username',username)

                const isMatch = await bcrypt.compare(password , pwdInDB[0].password)
                
                if(!isMatch){
                    return {message:'incorrect password'}
                }

                return {
                    message:'login successfully',
                }
            } catch (error) {
                console.log(error)
                return {error}
            }
        }
    })

    server.route({
        method: 'POST',
        path: '/comment/{commicId}',
        handler: async (request, h) => {
            try{
                const {comment} = request.payload
                const {commicId} = request.params
                console.log(comment,commicId)
                await knex('comments').insert({content: `${comment}` , commicId: `${commicId}`})
                const data = await knex.select().table('comments').where('commicId', commicId)
                console.log(data)
                return data
            }
            catch(e){
                console.log(e)
                throw new Error(e)
            }
        }
    });

    server.route({
        method: 'POST',
        path: '/manga/save/{userId}/{commicId}',
        handler: async (request, h) => {
            try{
                const {userId , commicId} = request.params

                const data = await knex.select().table('save_manga').where({
                    userId,
                    commicId
                })

                if(data.length){
                    return {
                        message: `user với id ${userId} đã save bộ truyện này`
                    }
                }
                
                await knex('save_manga').insert({userId,commicId})

                return {
                    message: "save truyện thành công"
                }
            }
            catch(e){
                console.log(e)
                throw new Error(e)
            }
        }
    });

    server.route({
        method: 'GET',
        path: '/manga/get/{userId}',
        handler: async (request, h) => {
            try{
                const {userId} = request.params

                const data = await knex.select().table('save_manga').where({
                    userId
                })

                return data
            }
            catch(e){
                console.log(e)
                throw new Error(e)
            }
        }
    });

    await server.start();
    console.log('Server running on %s', server.info.uri);
};

process.on('unhandledRejection', (err) => {

    console.log(err);
    process.exit(1);
});

init();