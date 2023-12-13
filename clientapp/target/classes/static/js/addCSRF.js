function addCSRFToken() {
  let token = $("meta[name='_csrf']").attr("content");
  // console.log(token);
  let header = $("meta[name='_csrf_header']").attr("content");
  // console.log(header);
  $(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
  });
}
const getUserProfile = () => {
  const profileId = $("#userId");
  const profileName = $("#userName");
  const porfileEmail = $("#userEmail");
  const profilePhone = $("#userPhone");
  const profileUsernameAcc = $("#usernameAcc");
  const profileRole = $("#userRole");

  $.ajax({
    url: "/profile",
    type: "GET",
    contentType: "application/json",
    dataType: "JSON",
    success: (res) => {
      profileId.text(res.id);
      profileName.text(res.name);
      porfileEmail.text(res.email);
      profilePhone.text(res.phone);
      profileUsernameAcc.text(res.user.username);
      profileRole.text(res.user.roles[0].name);
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Sorry, user not found!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });


$("#userUpdateId").on("click", () => {
  $.ajax({
    url: `/api/employee/${profileId.text()}`,
    type: "GET",
    success: (res) => {
      $("#e-profileId").val(res.id);
      $("#e-profileName").val(res.name);
      $("#e-profileEmail").val(res.email);
      $("#e-profilePhone").val(res.phone);
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Sorry, user not found!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

// update profile user
$("#u-profUser").click((e) => {
  e.preventDefault();

  const id = $("#e-profileId").val();
  const name = $("#e-profileName").val();
  const email = $("#e-profileEmail").val();
  const phone = $("#e-profilePhone").val();

console.log(id, name, email, phone)

  $.ajax({
    url: `/api/employee/${id}`,
    method: "PUT",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      name: name,
      email: email,
      phone: phone,
    }),
    success: (res) => {
      console.log(res);
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Profile has been updated!",
        showConfirmButton: false,
        timer: 2000,
      });
      $("#updateProfile").modal("hide");
      $("#profileModal").modal("hide");
    },
    error: () => {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Sorry, profile not updated!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});
}
