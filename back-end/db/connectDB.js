//config database
const option = {
    client: 'mysql2',
    connection: {
        host: 'localhost',
        port: 3306,
        user: 'root',
        password: '2142001',
        database: 'manga'
    }
}

// connect to database
const knex = require('knex')(option)

// console.log(knex.select('id').from('users').then(()=>{
//     console.log(1)
// }).catch(e=>console.log(e)))

module.exports = knex

