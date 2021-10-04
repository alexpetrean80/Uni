const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser')
const axios = require('axios')


const CLIENTS_SERVICE_URL = 'http://localhost:4000'
const DROIDS_SERVICE_URL = 'http://localhost:4001'
const PURCHASES_SERVICE_URL = 'http://localhost:4002'
const RECEIPTS_SERVICE_URL = 'http://localhost:4003'

const app = express()

app.use(cors())
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended: true}))

app.get('/', (req, res) => {
    console.log('here')
    res.send('Hello World!')
})
// client service routes
app.get('/client/{id}', async (req, res) => {
    try {
        const resp = await axios.get(CLIENTS_SERVICE_URL + '/client/' + req.params.id)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})
app.post('/client', async (req, res) => {
    try {
        const resp = await axios.post(CLIENTS_SERVICE_URL + '/client', req.body)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})
app.delete('/client/{id}', async (req, res) => {
    try {
        const resp = await axios.delete(CLIENTS_SERVICE_URL + '/client/' + req.params.id)
        return res.status(resp.status)
            .send()
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.put('/client/{id}', async (req, res) => {
    try {
        const resp = await axios.post(CLIENTS_SERVICE_URL + '/client/' + req.params.id, req.body)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500).json(e.message)
    }

})

app.get('/clients', async (req, res) => {
    try {
        const resp = await axios.get(CLIENTS_SERVICE_URL + '/client')
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }

})

app.get('/clients/{address}', async (req, res) => {
    try {
        const resp = await axios.get(CLIENTS_SERVICE_URL + '/clients/' + req.params.address)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

// droids service routes
app.get('/droid/{id}', async (req, res) => {
    try {
        const resp = await axios.get(DROIDS_SERVICE_URL + '/droid/' + req.params.id)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.post('/droid', async (req, res) => {
    try {
        const resp = await axios.post(DROIDS_SERVICE_URL + '/droid', req.body)
        return res.status(resp.status).json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.put('/droid/{id}', async (req, res) => {
    try {
        const resp = await axios.post(DROIDS_SERVICE_URL + '/droid/' + req.params.id, req.body)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})
app.delete('/droid/{id}', async (req, res) => {
    try {
        const resp = await axios.delete(DROIDS_SERVICE_URL + '/droid/' + req.params.id)
        return res.status(resp.status)
            .send()
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.get('/droids', async (_, res) => {
    try {
        const resp = await axios.get(DROIDS_SERVICE_URL + '/droids')
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.get('/droids/{model}', async (req, res) => {
    try {
        const resp = await axios.get(DROIDS_SERVICE_URL + '/droids/' + req.params.model)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

// purchase service routes
app.get('/purchase/{id}', async (req, res) => {
    try {
        const resp = await axios.get(PURCHASES_SERVICE_URL + '/purchase/' + req.params.id)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})
app.post('/purchase', async (req, res) => {
    try {
        const resp = await axios.post(PURCHASES_SERVICE_URL + '/purchase', req.body)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.delete('/purchase/{id}', async (req, res) => {
    try {
        const resp = await axios.delete(PURCHASES_SERVICE_URL + '/purchase/' + req.params.id)
        return res.status(resp.status)
            .send()
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})
app.put('/purchase/{id}', async (req, res) => {
    try {
        const resp = await axios.post(PURCHASES_SERVICE_URL + '/purchase/' + req.params.id, req.body)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.get('/purchases', async (req, res) => {
    try {
        const resp = await axios.get(PURCHASES_SERVICE_URL + '/purchases')
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.get('/purchases/{total}', async (req, res) => {
    try {

        const resp = await axios.get(PURCHASES_SERVICE_URL + '/purchases/' + req.params.model)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

// receipt service routes
app.get('/receipt/{id}', async (req, res) => {
    try {
        const resp = await axios.get(RECEIPTS_SERVICE_URL + '/receipt/' + req.params.id)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})
app.post('/receipt', async (req, res) => {
    try {
        const resp = await axios.post(RECEIPTS_SERVICE_URL + '/receipt', req.body)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.delete('/receipt/{id}', async (req, res) => {
    try {
        const resp = await axios.delete(RECEIPTS_SERVICE_URL + '/receipt/' + req.params.id)
        return res.status(resp.status)
            .send()
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})
app.put('/receipt/{id}', async (req, res) => {
    try {
        const resp = await axios.post(RECEIPTS_SERVICE_URL + '/receipt/' + req.params.id, req.body)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})
app.get('/receipts', async (req, res) => {
    try {
        const resp = await axios.get(RECEIPTS_SERVICE_URL + '/receipts')
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)

    }
})

app.get('/receipts/{total}', async (req, res) => {
    try {
        const resp = await axios.get(RECEIPTS_SERVICE_URL + '/receipts/' + req.params.model)
        return res.status(resp.status)
            .json(resp.data)
    } catch (e) {
        return res.status(500)
            .json(e.message)
    }
})

app.listen(8080, '0.0.0.0', () => {
    console.log('Listening on port 8080')
})