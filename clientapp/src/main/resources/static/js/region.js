$(document).ready(function () {
    $("#table-region").DataTable({
      ajax: {
        method: "GET",
        url: "api/region",
        dataSrc: "",
      },
  
      columns: [
        {
          data: null,
          render: function (data, type, row, meta) {
            return meta.row + 1;
          },
        },
        { data: "name" },
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
                    onclick="updateRegion(${data.id})"
                   
                  >
                    Update
                  </button>
                  <!-- Button trigger modal delete -->
                  <button
                    type="button"
                    class="btn btn-danger"
                    data-bs-toggle="modal"
                    data-bs-target="#delete"
                    onclick="deleteRegion(${data.id})"
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

      function getById(id){
        $.ajax({
          method: "GET",
          url: "api/region/" + id,
          dataType: "JSON",
          contentType: "application/json",
          success: (res) => {
            // console.log(res);
            $("#detail-id").val(`${res.id}`);
            $("#detail-name").val(`${res.name}`);
          },
          error: (err) => {
            console.log(err);
          },
        });
      }

      $("#create-region").click((event) => {
        event.preventDefault();
      
        let valueName = $("#create-name").val();
        
      
        $.ajax({
          method: "POST",
          url: "api/region",
          dataType: "JSON",
          contentType: "application/json",
          beforeSend: addCSRFToken(),
          data: JSON.stringify({
            name: valueName,
          }),
          success: (res) => {
            $("#create").modal("hide");
            $("#table-region").DataTable().ajax.reload();
            Swal.fire({
              position: "center",
              icon: "success",
              title: "Your Region has been craeted...",
              showConfirmButton: false,
              timer: 2000,
            });
            $("#create-name").val("");
          },
          error: (err) => {
            if (err.status === 500) {
              Swal.fire({
                position: "center",
                icon: "warning",
                title: "Region already exists. Please use a different name.",
                showConfirmButton: false,
                timer: 2000,
              });
            } else {
              console.log(err);
            }
          },
        });
      });

      function updateRegion(id) {
        $.ajax({
            method: "GET",
            url: "api/region/" + id,
            dataType: "JSON",
            contentType: "application/json",
            success: (res) => {
                $("#update-id").val(res.id);
                $("#update-name").val(res.name);
            },
            error: (err) => {
                console.log(err);
            },
        });
    }
    $("#update-region").click((event) => {
        event.preventDefault();
        
        let valueId = $("#update-id").val();
        let ValueName = $("#update-name").val();
        $.ajax({
            method: "PUT",
            url: "api/region/" + valueId,
            dataType: "JSON",
            contentType: "application/json",
            beforeSend: addCSRFToken(),
            data: JSON.stringify({
                name: ValueName,
            }),
            
            success: (res) => {
              $("#update").modal("hide");
              $("#table-region").DataTable().ajax.reload();
              Swal.fire({
                position: "center",
                icon: "success",
                title: "Your Region has been updated...",
                showConfirmButton: false,
                timer: 2000,
              });
              $("#update-name").val("");
            },
            error: (err) => {
              console.log(err);
            },
          });
        });
    
        //delete
        function deleteRegion(id){
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
                  url: "api/region/" + id,
                  dataType: "JSON",
                  contentType: "application/json",
                  beforeSend: addCSRFToken(),
                  success: (res) => {

                    swalWithBootstrapButtons.fire({
                      title: "Deleted!",
                      text: "Your data has been deleted.",
                      icon: "success",
                    });

                    $("#table-region").DataTable().ajax.reload();
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
          
         


