<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<hr>
<form id="upload" action="${pageContext.request.contextPath}/base/rest/upload" method="post"
      enctype="multipart/form-data">
    <input type="file" name="fileName"/>
    <input type="submit" value="上传"/>
</form>
<hr>
<button onclick="getWithAnnotation();">GET-url-参数带注解</button>
<button onclick="getNoAnnotation();">GET-url-参数缺省注解</button>
<button onclick="getNoAnnotationObj();">GET-url-参数缺省注解-转对象</button>
<hr>
<button onclick="postWithAnnotation();">POST-表单带注解</button>
<button onclick="postNoAnnotation();">POST-表单缺省注解</button>
<button onclick="postNoAnnotationlose();">POST-表单缺省注解-缺少参数</button>
<button onclick="postWithAnnotationPathAndBody();">POST-表单-url参数-请求体参数</button>
<button onclick="postNoAnnotationObj();">POST-表单缺省注解转对象</button>
<button onclick="postWithAnnotationObj();">POST-json带注解转对象</button>
<button onclick="postWithAnnotationObjStr();">POST-json带注解对象字符串</button>
<hr>
<button onclick="removeNoAnnotation();">DELETE-url-无注解</button>
<button onclick="removeWithAnnotation();">DELETE-url-带注解</button>
<hr>
<button onclick="putNoAnnotation();">PUT-表单-无注解</button>
<button onclick="putWithAnnotation();">PUT-json-注解</button>

</body>
<script src="https://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script>
    let member = {
        id: 123,
        name: 'zhn',
        age: 24
    };

    function uploadFile() {
        $.ajax({
            attr: "POST",
            url: "${pageContext.request.contextPath}/base/rest/upload",
            data: $('#upload').serialize(),  //对form表单进行序列化，从而将form表单中的所有参数传递到服务端。
            success: function (result) {
            },
            error: function (err) {
            }
        });
    }

    function getWithAnnotation() {
        $.ajax({
            attr: 'get',
            url: '${pageContext.request.contextPath}/base/rest/get/123?name=zhn&age=24',
            dataType: 'text',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function getNoAnnotation() {
        $.ajax({
            attr: 'get',
            url: '${pageContext.request.contextPath}/base/rest/get?id=123&name=zhn&age=24',
            dataType: 'text',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function getNoAnnotationObj() {
        $.ajax({
            attr: 'get',
            url: '${pageContext.request.contextPath}/base/rest/getObj?id=123&name=zhn&age=24',
            dataType: 'text',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postWithAnnotation() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/base/rest/post',
            dataType: 'text',
            data: member,
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postNoAnnotation() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/base/rest/post2',
            dataType: 'text',
            data: member,
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postNoAnnotationlose() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/base/rest/post2lose',
            dataType: 'text',
            data: {id: 123, age: 24},
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postWithAnnotationPathAndBody() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/base/rest/post/pathandbody',
            dataType: 'text',
            data: JSON.stringify(member),
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postNoAnnotationObj() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/base/rest/postObj',
            dataType: 'text',
            data: member,
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postWithAnnotationObj() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/base/rest/postObj2',
            dataType: 'text',
            contentType: 'application/json',
            data: JSON.stringify(member),
            success: function (result) {
                console.log(result);
            }
        });
    }

    function postWithAnnotationObjStr() {
        $.ajax({
            attr: 'post',
            url: '${pageContext.request.contextPath}/base/rest/postObjStr',
            dataType: 'text',
            contentType: 'application/json',
            data: JSON.stringify(member),
            success: function (result) {
                console.log(result);
            }
        });
    }

    function removeNoAnnotation() {
        $.ajax({
            attr: 'delete',
            url: '${pageContext.request.contextPath}/base/rest/del?id=12&name=zhn',
            dataType: 'text',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function removeWithAnnotation() {
        $.ajax({
            attr: 'delete',
            url: '${pageContext.request.contextPath}/base/rest/del/12?name=zhn',
            dataType: 'text',
            success: function (result) {
                console.log(result);
            }
        });
    }

    function putNoAnnotation() {
        $.ajax({
            attr: 'put',
            url: '${pageContext.request.contextPath}/base/rest/put',
            dataType: 'text',
            data: member,
            success: function (result) {
                console.log(result);
            }
        });
    }

    function putWithAnnotation() {
        $.ajax({
            attr: 'put',
            url: '${pageContext.request.contextPath}/base/rest/putObj',
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
