var url = "/api/hangdt";
var rowTemplate = "{{#each hangdt}}" +
    '<tr style="font-size: 17px; line-height: 35px; ">' +
    '<td style="text-align: center; line-height: 20px">{{hang_id}}</td>' +
    '<td style="text-align: center; line-height: 20px;">{{hang_name}}</td>' +
    '<td style="width: 60px; display: flex; flex-direction: row; justify-content: space-between; align-items: center; margin: 0 auto">' +
    '<button class="edit" hang-id="{{hang_id}}" style="border: none; background: white; cursor: pointer;"><i class="far fa-edit" style="color: green;"></i></button> ' +
    '<button class="delete" hang-id="{{hang_id}}" style="border: none; background: white; cursor: pointer;"><i class="fas fa-trash" style="color: red;"></i></button>' +
    '</td>' +
    "</tr>" +
    "{{/each}}";
var rowHbs = Handlebars.compile(rowTemplate);

function loadList() {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            var rows = rowHbs({
                hangdt: data
            });
            $("#tableBody").html(rows);
            bindClick();
            bindDelete();
            exitModal();
        });
}
loadList();

// chức năng sửa
// show modal
var insertModal = new bootstrap.Modal(document.getElementById('editModal'), {
    keyboard: false
});

// nut them
$("#them").click(function() {
    insertModal.show();
    // nội dung forrm thêm; chú ý k để value
    var insertForm = '<div class="mb-3">' +
        '<label for="hang_name" class="form-label">Tên hãng</label>' +
        '<input name="hang_name" value="" class="form-control" id="hang_name" placeholder="Tên hãng" >' +
        '</div>';
    // thêm nội dung vào modal 
    $("#editForm").html(insertForm);


});
$("#save").click(function() {
    // var arr = document.getElementsByTagName("input");
    // var hangName = arr[0].value;

    // if (hangName == "") {
    //     alert("Không được để trống thông tin!")

    // }
    var hang = FormDataJson.formToJson(document.querySelector("form"));
    var insertOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(hang)

    };
    hang = fetch(url, insertOption)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            loadList();
        })
    insertModal.hide();



});
// luu noi dung moi them


// sửa
var editTemplateStr = '<input type="hidden" name="hang_id" id-hang="hang_id" value = "{{hang_id}}">' +
    '<div class="mb-3">' +
    '<label for="hang_name" class="form-label">Tên hãng</label>' +
    '<input name="hang_name" value="{{hang_name}}" class="form-control" id="hang_name" placeholder="Tên hãng">' +
    '</div>';
var editHbs = Handlebars.compile(editTemplateStr);

function bindClick() {
    $(".edit").click(function() {
        insertModal.show();
        var id = $(this).attr("hang-id");
        fetch(url + "/" + id)
            .then(res => res.json())
            .then(data => {
                var editHtml = editHbs(data);
                $("#editForm").html(editHtml);
            });
    });
}

$("#save").click(function() {
    var hang = FormDataJson.formToJson(document.querySelector("form"));
    var id = $(this).attr("id-hang");
    var editOption = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(hang)

    };

    hang = fetch(url + "/" + id, editOption)
        .then(response => response.json())
        .then(data => {
            loadList();
        })
    insertModal.hide();
});

// xóa
function bindDelete() {
    $(".delete").click(function() {
        deleteHang($(this))
    })
}

function deleteHang($button) {
    var id = $button.attr("hang-id");
    alert("Xác nhận xóa");
    var deleteOption = {
        method: "delete",
        headers: {
            'Content-Type': 'application/json'
        }
    };
    hang = fetch(url + "/" + id, deleteOption)
        .then(response => {
            loadList();
        });
}

function exitModal() {
    $("#close").click(function() {
        alert("Xác nhận thoát!")
        insertModal.hide()
    })
}