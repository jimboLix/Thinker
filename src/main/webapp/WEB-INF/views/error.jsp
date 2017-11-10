<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>出错啦</title>
</head>
<body>
    <h3>出错啦</h3>
<a href="javascript:void(0)" id="detail">查看详情</a>
<div id="meg"></div>
</body>
<script src="/res/jquery.js"></script>
<script type="text/javascript">
    $(function(){
        var msg = ${meg};
        $("#detail").click(function(){
            $("#msg").text(msg);
        });
    })
</script>
</html>
