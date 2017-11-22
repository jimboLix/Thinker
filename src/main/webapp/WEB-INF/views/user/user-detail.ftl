<html>
<body>
    <h5>用户详情</h5>
<form>
    <table>
        <tr>
            <td>用户id</td>
            <td>用户名称</td>
        </tr>
        <tr>
            <td><input name="id" type="text" value="${(user.id)!}"></td>
            <td><input name="name" type="text" value="${(user.name)!}"></td>
        </tr>
    </table>
</form>
</body>
</html>