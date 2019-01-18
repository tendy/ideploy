
function validateParam() {
  var message = '';

  var username = $('#username').val();
  var password = $('#password').val();
  if(!username || username==''){
    message = "用户名不能为空";
  }else if(!password || password==''){
    message = "密码不能为空";
  }
  if(message != ''){
    $('.alert span:first').html(message);
    $('.alert').prop("style", "display:block");
    return false;
  }
  return true;
}

function login() {
  if(!validateParam()){
    return false;
  }
  var username = $('#username').val();
  var password = $('#password').val();

  $.ajax({
    type: 'POST',
    url: '/login',
    data: {
      username: username,
      password: password
    },
    dataType: 'json',
    contentType: "application/x-www-form-urlencoded; charset=utf-8",
    success: function (data) {
      if (data.success) {
        window.location.href = "/index.html";
      }else {
        $('.alert span:first').html(data.message);
        $('.alert').prop("style", "display:block");
      }
    },
    error: function (data) {
      $('.alert span:first').html("服务器内部异常");
      $('.alert').prop("style", "display:block");
    }

  });
}