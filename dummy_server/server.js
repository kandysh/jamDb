const express = require('express');
const http = require('http');
const fs = require('fs');
const cors = require('cors');

const port = 3030;
const filePath = '../jamdb/data.json';

const app = express();
app.use(cors());

const server = http.createServer(app);

app.get('/api/v1/content/list', (req, res) => {
  fs.readFile(filePath, (err, data) => {
    if (err) {
      res.writeHead(500, { 'Content-Type': 'text/plain' });
      res.end('Internal server error');
    } else {
      res.setHeader('Access-Control-Allow-Origin', '*');
      res.writeHead(200, { 'Content-Type': 'application/json' });
      res.end(data);
    }
  });
});

app.post('/api/v1/auth/register', (req, res) => {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.writeHead(200, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify({ message: 'User registered' }));
  console.log('User registered');
  console.log(req.body);
  let body = '';
    req.on('data', chunk => {
      body += chunk.toString();
    });
    req.on('end', () => {
      console.log('Received POST request:', body);
      res.end('Received POST request');
    });
});
  
app.post('/api/v1/auth/login', (req, res) => {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.writeHead(200, { 'Content-Type': 'application/json' });
  res.end(JSON.stringify({ message: 'User LoggedIn' }));
  console.log(req.body);
  let body = '';
    req.on('data', chunk => {
      body += chunk.toString();
    });
    req.on('end', () => {
      console.log('Received POST request:', body);
      res.end('Received POST request');
    });
});

server.listen(port, () => {
  console.log(`Server listening on port ${port}`);
});
