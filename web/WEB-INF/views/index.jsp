<%--
  Created by IntelliJ IDEA.
  User: migusdn
  Date: 20. 3. 14.
  Time: 오전 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>OurAzit</title>
<body class="">

<!-- Header -->
<header id="header" style="z-index: -1;">
  <div class="userId">
    admin

  </div>

  <div id="logout" style="position: absolute; right:0;">
    <i class="fas fa-sign-out-alt" aria-hidden="true"></i>
  </div>

</header>
<div class="container modal-open">

  <div class="data">
    <div style="display: flex; margin: 1rem;">
      <div class="image">
        <img class="profileImg" src="blob:http://ourazit.com/1e6e1787-9889-431b-bb40-4872fa2aff0b" style="width: 5rem;"
             alt="">
      </div>
      <div style="display: flex; margin: 0 auto;">
        <div class="userInfo" style="color: white;">
          <div class="num">
            <b class="post_ctn">1</b>
          </div>
          게시물
        </div>
        <div class="userInfo">
          <div class="num">
            <b>0</b>
          </div>
          팔로워
        </div>
        <div class="userInfo">
          <div class="num">
            <b>0</b>
          </div>
          팔로잉
        </div>
      </div>
    </div>
    <div style="margin: 1rem 1rem 0.5rem 1rem">이름</div>
    <div id="modal"
         style="color: white; text-align: center; margin: 0 1rem; border: 1px solid gray; border-radius: 5px; padding: 0.2rem 0;">
      프로필수정
    </div>
  </div>
  <div class="modal">
    <div class="header" style="background: #262626; height: 60px; display: flex;">
      <div class="close-modal" style="margin: auto;">
        <b>취소</b>
      </div>
      <div style="margin: auto;">
        <b>프로필 편집</b>
      </div>
      <div class="submit" style="margin: auto;">
        <b>완료</b>
      </div>
    </div>
    <div class="body">
      <div class="profile">
        <div class="profile_img">
          <img class="profileImg" src="blob:http://ourazit.com/1e6e1787-9889-431b-bb40-4872fa2aff0b"
               style="width: 5rem;" alt="">
        </div>
        <div class="profile_upload">
          프로필 사진 바꾸기
        </div>
      </div>
      <div class="user_info">
        <div class="user_name">
          <div class="modify">이름</div>
          <div>asdf</div>
        </div>
        <div class="user_nick">
          <div class="modify">닉네임</div>
          <div>ㅁㄴㅇㄹ</div>
        </div>
        <div class="edit_pwd"></div>
      </div>


    </div>
    <input type="file" accept="image/*;capture=camera" id="profile" style="display: none">
  </div>

  <section class="wrapper" id="main" style="padding: 2rem 0px 70px; z-index: -1;">
    <div class="preview_wrapper" style="line-height: 0; display:block;">
      <!-- 				<div class="preview"><img src="https://i.picsum.photos/id/239/700/700.jpg"></div
                      ><div class="preview"><img src="https://i.picsum.photos/id/239/700/700.jpg"></div
                      ><div class="preview"><img src="https://i.picsum.photos/id/239/700/700.jpg"></div>
                   -->
      <div class="preview" postid="80"><img
              src="http://api.ourazit.com/img/2020/04/21/s_7d006a68-13df-40ee-84b5-39637a834426_blob"></div>
    </div>

    <div style="display: block;" id="load"></div>

  </section>
  <script>


    $(document).on('click', '.profile_upload', function () {
      alert('is working');
      $('#profile').click();

    });
    var file = document.getElementById("profile");
    $(document).on('change', '#profile', function (e) {
      var file = e.target.files[0];
      console.log(file);
      $('.profileImg').attr('src', URL.createObjectURL(e.target.files[0]));
      console.log(URL.createObjectURL(e.target.files[0]));
    });
    $(document).ready(function () {
      var ctn = 0;
      $.each([{
        "post_id": "80",
        "user_id": "admin",
        "post_title": "testpost",
        "post_content": "{\"comment\":\"test\",\"contents\":[[{\"mediaType\":\"png\",\"originalFileName\":\"blob\",\"savedPath\":\"/2020/04/21\",\"fileSize\":66557,\"savedName\":\"7d006a68-13df-40ee-84b5-39637a834426_blob\"}],[{\"mediaType\":\"png\",\"originalFileName\":\"blob\",\"savedPath\":\"/2020/04/21\",\"fileSize\":66557,\"savedName\":\"819194e8-18d0-40a8-b6d7-53093e318701_blob\"}],[{\"mediaType\":\"png\",\"originalFileName\":\"blob\",\"savedPath\":\"/2020/04/21\",\"fileSize\":66557,\"savedName\":\"490c91a3-fb14-4fde-a761-8b28e1f80f96_blob\"}]]}",
        "post_date": "2020-04-21 23:53:36.0",
        "post_edit_date": "2020-04-21 23:53:36.0"
      }], function (index, vo) {
        renderList(false, vo);
        ctn++;
      })
      $('.post_ctn').text(ctn);
    });
    let renderList = function (mode, vo) {
      // 리스트 html을 정의
      var post_content = JSON.parse(vo.post_content);
      console.log(post_content);
      console.log(post_content.contents[0][0].savedPath);
      let html = ""
      html += '<div class="preview" postid="' + vo.post_id + '">';
      html += '<img src="http://api.ourazit.com/img'
              + post_content.contents[0][0].savedPath
              + '/s_'
              + post_content.contents[0][0].savedName
              + '"></div>';
      $(".preview_wrapper").append(html);

    }
    $(document).on('click', '.preview', function () {
      var post_id = $(this).attr('postid');
      location.href = '/post/' + post_id;
      // your function here
    });
    $('#modal').click(function () {
      $('.container').addClass('modal-open');
      $('body').addClass('')
      $('.wrapper').css('z-index', -1);
      $('#header').css('z-index', -1);
    });

    $('.close-modal').click(function () {
      $('.container').removeClass('modal-open');
      setTimeout(function () {
        $('.wrapper').css('z-index', 0);
        $('#header').css('z-index', 0);
      }, 600);
    });
    $('#logout').click(function () {
      location.href = '/Logout';
    })
    $('.submit').click(function () {
      alert('is working');
      const formData = new FormData();
      file = $('#profile')[0];
      console.log(file.files[0]);
      if (file.files.length != 0) {
        alert('is not empty');
        var fData = new FormData();
        fData.append('file', file.files[0]);
        console.log('upload start');
        $.ajax({
          type: 'POST',
          enctype: 'multipart/form-data',
          url: 'http://api.ourazit.com/profileupload',
          data: fData,
          processData: false,
          contentType: false,
          async: false,
          success: function (result) {
            console.log('uplaod success');
            console.log(result);
          },
          error: function (e) {
            console.log('upload fail');
            console.log(e);
          },

        });
      } else {
        alert('is empty');
      }
    });


  </script>

</div>
<!-- Footer -->
<footer id="footer">
  <div class="MenuIcon" onclick="location.href='/';">
    <i class="fas fa-home" aria-hidden="true"></i>
  </div>
  <div class="MenuIcon" onclick="location.href='/search';">
    <i class="fas fa-search" aria-hidden="true"></i>
  </div>
  <div class="MenuIcon" onclick="location.href='/add';">
    <i class="far fa-plus-square" aria-hidden="true"></i>
  </div>
  <div class="MenuIcon" onclick="location.href='/follow';">
    <i class="fas fa-heart" aria-hidden="true"></i>
  </div>
  <div class="MenuIcon" onclick="location.href='/mypage';">
    <i class="fas fa-user" style="color: #444444;" aria-hidden="true"></i>
  </div>
</footer>

</body>
</html>