var express = require('express');
var db = require('./db')
var router = express.Router();

router.get('/:phone', function(req,res,next){
  var phone = req.params.phone;

  var sql = "select * " +"where phone = ? limit 1;";
  console.log("sql : " = sql);

db.get().query(sql, phone, function (err,rows){
  console.log("rows : " + JSON.stringify(rows));
  console.log("row.length : " + rpws.length);
  if(rows.length > 0){
    res.json(rows[0]);
  } else {
    res.sendStatus(400);
  }
});
});

router.post('/phone', function(req, res){
  var phone = req.body.phone;

  var sql_count = "select count(*) as cnt" + "where phone = ?;";
  console.log("sql_count : " + sql_count);

  var sql_insert = "insert into bestfood_member (phone) values(?);";

  db.get().query(sql_count, phone, function (err,rows){
    console.log(rows);
    console.log(rows[0].cnt);

    if(rows[0].cnt>0){
      return res.sendStatus(400);
    }

    db.get().query(sql_insert, phone, function (err,result){
      if(err) return res.sendStatus(400);
      res.status(200).send(''+result.insertId);
    });
  });
});
module.exports = router;
