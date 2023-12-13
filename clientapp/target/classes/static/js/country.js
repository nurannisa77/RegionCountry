$(document).ready(function () {
    $("#table-country").DataTable({
      ajax: {
        method: "GET",
        url: "api/country",
        dataSrc: "",
      },
      columns: [
        {
          data: null,
          render: (data, row, type, meta) => {
            return meta.row + 1;
          },
        },
        { data: "code" },
        { data: "name" },
        { data: "region.name" },
        {
          data: null,
          render: (data) => {
            return `
                <div class="d-flex gap-3 justify-content-center">
                  <!-- Button trigger modal detail -->
                  <button
                    type="button"
                    class="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#detail"
                    onclick="getById(${data.id})"
                  >
                    Detail
                  </button>
                  <!-- Button trigger modal update -->
                  <button
                    type="button"
                    class="btn btn-warning"
                    data-bs-toggle="modal"
                    data-bs-target="#update"
                    onclick="beforeUpdate(${data.id})"
                   
                  >
                    Update
                  </button>
                  <!-- Button trigger modal delete -->
                  <button
                    type="button"
                    class="btn btn-danger"
                    data-bs-toggle="modal"
                    data-bs-target="#delete"
                    onclick="deleteCountry(${data.id})"
                  >
                    Delete
                  </button>
                </div>
              `;
              },
            },
          ],
        });
      });

      $.ajax({
        method: "GET",
        url: "api/region",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
          data.forEach(function (region) {
            $("#create-region-country").append(
              `<option value="` + region.id + `">` + region.name + "</option>"
            );
          });
        },
        error: (err) => {
          console.log(err);
        },
      });
    

      function getById(id) {
        $.ajax({
          method: "GET",
          url: "api/country/" + id,
          dataType: "JSON",
          contentType: "application/json",
          success: (res) => {
            $("#detail-id").val(`${res.id}`);
            $("#detail-name").val(`${res.name}`);
            $("#detail-code").val(`${res.code}`);
            $("#detail-region").val(`${res.region.name}`);
          },
          error: (err) => {
            console.log(err);
          },
        });
      }

      $("#create-country").click((event) => {
        event.preventDefault();
      
        let valueName = $("#create-name").val();
        let valueCode = $("#create-code").val();
        let valueRegion = $("#create-region-country").val();
      
        $.ajax({
          method: "POST",
          url: "api/country",
          dataType: "JSON",
          contentType: "application/json",
          beforeSend: addCSRFToken(),
          data: JSON.stringify({
            name: valueName,
            code: valueCode,
            region: { id: valueRegion },
          }),
          success: (res) => {
            $("#create").modal("hide");
            $("#table-country").DataTable().ajax.reload();
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Your Region has been updated...",
                showConfirmButton: false,
                timer: 2000,
              });
             
            },
            error: (err) => {
              console.log(err);
            $("#create-name").val("");
            $("#create-code").val("");
            $("#create-region-country").val("");

          },
        });
      });
  
  // Update Country
  function beforeUpdate(id) {
    $.ajax({
      method: "GET",
      url: "api/region/",
      dataType: "JSON",
      contentType: "application/json",
      success: (res) => {
        let selectElement = $("#update-region-country");
        selectElement.empty();

        res.forEach((region) => {
            let option = `<option value = "${region.id}"> ${region.name}</option>`;
            selectElement.append(option);
        });
       
      },
    });

  $.ajax({
    method: "GET",
    url: "api/country/" + id,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
        console.log(res);

      $("#update-id").val(`${res.id}`);
      $("#update-name").val(`${res.name}`);
      $("#update-code").val(`${res.code}`);
      $("#update-region-country").val(`${res.region.id}`);
    },
    error: (err) => {
      console.log(err);
    },
  });
}

$("#update-country").click((event) => {
  event.preventDefault();

  let valueId = $("#update-id").val();
  let valueName = $("#update-name").val();
  let valueCode = $("#update-code").val();
  let valueRegion = $("#update-region-country").val();

  $.ajax({
    method: "PUT",
    url: "api/country/" + valueId,
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      name: valueName,
      code: valueCode,
      region: { id: valueRegion },
    }),
    success: (res) => {
      $("#update").modal("hide");
      $("#table-country").DataTable().ajax.reload();
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Your Region has been updated...",
        showConfirmButton: false,
        timer: 2000,
      });
      $("#update-code").val("");
      $("#update-name").val("");
      $("#update-region-country").val("");
      
     
    },
    error: (err) => {
      console.log(err);
      $("#update").modal("hide");
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Your Region can not created",
    });
    $("#update-code").val("");
    $("#update-name").val("");
    $("#select").val("");
},
});
});
      
function deleteCountry(id){
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger",
    },
    buttonsStyling: false,
  });
  
  swalWithBootstrapButtons
      .fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: "Yes, delete it!",
      cancelButtonText: "No, cancel!",
      reverseButtons: true,
    
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          method: "DELETE",
          url: "api/country/" + id,
          dataType: "JSON",
          contentType: "application/json",
          beforeSend: addCSRFToken(),
          success: (res) => {

            swalWithBootstrapButtons.fire({
              title: "Deleted!",
              text: "Your data has been deleted.",
              icon: "success",
            });

            $("#table-country").DataTable().ajax.reload();
            },
            error: (err) => {
              console.log(err);
            },
          });
        } else if (

          result.dismiss === Swal.DismissReason.cancel
        ) {
          swalWithBootstrapButtons.fire({
            title: "Cancelled",
            text: "Your  data is safe!",
            icon: "error",
          });
        }
      });
  }
  