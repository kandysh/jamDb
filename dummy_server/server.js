const express = require('express');
const http = require('http');
const fs = require('fs');
const cors = require('cors');

const port = 6969;
const filePath = '../jamdb/data.json';

const app = express();
app.use(cors());

const server = http.createServer(app);

app.get('/api/media', (req, res) => {
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

server.listen(port, () => {
  console.log(`Server listening on port ${port}`);
});
