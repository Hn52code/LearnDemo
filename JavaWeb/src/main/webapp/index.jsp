<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<hr>
<form action="${pageContext.request.contextPath}/parameter" method="post"
      enctype="multipart/form-data">
    <input attr="file" name="fileName" placeholder="文件名..." value=""/>
    <input attr="submit" value="上传"/>
</form>
<hr>
<button onclick="get();">GET-url</button>
<button onclick="post();">POST-表单</button>
<button onclick="update();">PUT-表单</button>
<button onclick="remove();">DELETE-url</button>
<hr>
<button onclick="postNoUrlParameter();">POST-url不带参数</button>
<button onclick="postUrlParameter();">POST-url带参数</button>
<button onclick="postJsonStr();">POST-json字符</button>
<button onclick="putJsonStr();">PUT-json字符</button>

</body>
<script src="https://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script>
    let member = {
        id: 123,
        name: 'zhn',
        age: 24
    };


    function get() {
        $.ajax({
            attr: 'get',
            url: '${pageContext.request.contextPath}/rest?id=123&method=put',
            dataType: 'json',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function post() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/rest',
            dataType: 'text',
            data: member,
            success: function (result) {
                console.log(result);
            }
        });
    }

    function update() {
        $.ajax({
            attr: 'put',
            url: '${pageContext.request.contextPath}/rest',
            dataType: 'text',
            data: member,
            success: function (result) {
                console.log(result);
            }
        });
    }

    function remove() {
        $.ajax({
            attr: 'delete',
            url: '${pageContext.request.contextPath}/rest?id=12345646&method=delete',
            dataType: 'text',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postNoUrlParameter() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/parameter',
            dataType: 'text',
            data: member,
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postUrlParameter() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/parameter?name=paian&age=12',
            dataType: 'text',
            data: member,
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postJsonStr() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/parameter',
            dataType: 'text',
            data: JSON.stringify(member),
            contentType: 'application/json',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function putJsonStr() {
        $.ajax({
            attr: 'put',
            url: '${pageContext.request.contextPath}/parameter',
            dataType: 'text',
            data: JSON.stringify(member),
            contentType: 'application/json',
            success: function (result) {
                console.log(result);
            }
        });
    }

</script>


</html>

