var url = "/api/mausacdt";
var rowTemplate = "{{#each mausac}}" +
    '<tr style="font-size: 17px; line-height: 35px; ">' +
    '<td style="text-align: center; line-height: 20px">{{mausac_id}}</td>' +
    '<td style="text-align: center; line-height: 20px;">{{mausac_name}}</td>' +
    '<td style="width: 60px; display: flex; flex-direction: row; justify-content: space-between; align-items: center; margin: 0 auto">' +
    '<button class="editMau" mau-id="{{mausac_id}}" style="border: none; background: white; cursor: pointer;"><i class="far fa-edit" style="color: green;"></i></button> ' +
    '<button class="deleteMau" mau-id="{{mausac_id}}" style="border: none; background: white; cursor: pointer;"><i class="fas fa-trash" style="color: red;"></i></button>' +
    '</td>' +
    "</tr>" +
    "{{/each}}";
var rowMauHbs = Handlebars.compile(rowTemplate);

function loadListMau() {
    fetch(url)
        .then(res => res.json())
        .then(dataMau => {
            var rowMau = rowMauHbs({
                mausac: dataMau
            });
            $("#tableMauBody").html(rowMau);
            bindClickMau();
            bindDeleteMau();
            exitModalMau();
        });
}
loadListMau();

// chức năng sửa
// show modal
var insertMauSacModal = new bootstrap.Modal(document.getElementById('editMauModal'), {
    keyboard: false
});

// nut them
$("#themMau").click(function() {
    insertMauSacModal.show();
    // nội dung forrm thêm; chú ý k để value
    var insertMauForm = '<div class="mb-3">' +
        '<label for="mausac_name" class="form-label">Màu sắc</label>' +
        '<input name="mausac_name" value="" class="form-control" id="mausac_name" placeholder="Màu sắc">' +
        '</div>';
    // thêm nội dung vào modal 
    $("#editMauForm").html(insertMauForm);


});
$("#saveMau").click(function() {
    var mausac = FormDataJson.formToJson(document.querySelector("form"));
    var insertMauOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(mausac)

    };
    mausac = fetch(url, insertMauOption)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            loadListMau();
        })
    insertMauSacModal.hide();
});
// luu noi dung moi them


// sửa
var editMauTemplateStr = '<input type="hidden" name="mausac_id" id-mausac="mausac_id" value = "{{mausac_id}}">' +
    '<div class="mb-3">' +
    '<label for="mausac_name" class="form-label">Màu sắc</label>' +
    '<input name="mausac_name" value="{{mausac_name}}" class="form-control" id="mausac_name" placeholder="Màu sắc">' +
    '</div>';
var editMauHbs = Handlebars.compile(editMauTemplateStr);

function bindClickMau() {
    $(".editMau").click(function() {
        insertMauSacModal.show();
        var id = $(this).attr("mau-id");
        fetch(url + "/" + id)
            .then(res => res.json())
            .then(data => {
                var editMauHtml = editMauHbs(data);
                $("#editMauForm").html(editMauHtml);
            });
    });
}

$("#saveMau").click(function() {
    var mausac = FormDataJson.formToJson(document.querySelector("form"));
    var id = $(this).attr("id-mausac");
    var editMauOption = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(mausac)

    };

    mausac = fetch(url + "/" + id, editMauOption)
        .then(response => response.json())
        .then(data => {
            loadListMau();
        })
    insertMauSacModal.hide();
});

// xóa
function bindDeleteMau() {
    $(".deleteMau").click(function() {
        deletemausac($(this))
    })
}

function deletemausac($button) {
    var id = $button.attr("mau-id");
    alert("Xác nhận xóa");
    var deleteMauOption = {
        method: "delete",
        headers: {
            'Content-Type': 'application/json'
        }
    };
    mausac = fetch(url + "/" + id, deleteMauOption)
        .then(response => {
            loadListMau();
        });
}

function exitModalMau() {
    $("#closeMau").click(function() {
        alert("Xác nhận thoát!")
        insertMauSacModal.hide()
    })
}