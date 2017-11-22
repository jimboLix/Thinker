<#include "common/common.ftl"/>
<html>
    <form action="${base}/home/doLogin.do">
        <table>
            <tr>
                <td>账号</td>
                <td><input type="text" name="id"></td>
            </tr>
            <tr><td colspan="2"><input type="submit" value="Login"></td></tr>
        </table>
    </form>
<script>
    $(function () {
        console.log(${base});
    })
</script>
</html>