var express = require('express');
var db = require('./db')
var router = express.Router();

//member/:phone
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

//member/info
router.post('/info', function(req, res) {
  var phone = req.body.phone;
  var name = req.body.name;
  var sextype = req.body.sextype;
  var birthday = req.body.birthday;

  console.log({name, sextype, birthday, phone});

  var sql_count = "select count(*) as cnt " +
            "from bestfood_member " + 
            "where phone = ?;";

  var sql_insert = "insert into bestfood_member (phone, name, sextype, birthday) values(?, ?, ?, ?);";
  var sql_update = "update bestfood_member set name = ?, sextype = ?, birthday = ? where phone = ?; ";
  var sql_select = "select seq from bestfood_member where phone = ?; ";
  
  db.get().query(sql_count, phone, function (err, rows) {
    if (rows[0].cnt > 0) {
      console.log("sql_update : " + sql_update);

      db.get().query(sql_update, [name, sextype, birthday, phone], function (err, result) {
        if (err) return res.sendStatus(400);
        console.log(result);

        db.get().query(sql_select, phone, function (err, rows) {
          if (err) return res.sendStatus(400);

          res.status(200).send('' + rows[0].seq);
        });
      });
    } else {
      console.log("sql_insert : " + sql_insert);

      db.get().query(sql_insert, [phone, name, sextype, birthday], function (err, result) {
        if (err) return res.sendStatus(400);

        res.status(200).send('' + result.insertId);
      });
    }
  });
});
module.exports = router;
