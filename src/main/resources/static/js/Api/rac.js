var url = "/api/sanphamdt";
var rowDanhSachDT = "{{#each dienthoai}}" +
    "<tr>" +
    '<td style="text-align: center;">{{sanpham_id}}</td>' +
    '<td style="text-align: center; font-weight: bold;">{{sanpham_name}}</td>' +
    '<td style="text-align: center;">{{hang.hang_name}}</td>' +
    '<td style="text-align: center; font-weight: bold;">{{mausac.mausac_name}}</td>' +
    '<td style="text-align: center; font-weight: bold;">{{sanpham_soluong}}</td>' +
    '<td style="text-align: justify; text-indent: 20px;">{{sanpham_mota}}</td>' +
    '<td style="text-align: center; font-weight: bold;">{{sanpham_giaban}} đồng</td>' +
    '<td style="text-align: justify;text-indent: 20px;">{{sanpham_lienhe}}</td>' +
    '<td style="width: 60px; display: flex; flex-direction: row; justify-content: space-between; align-items: center; margin: 0 auto">' +
    '<button class="edit" sp-id="{{sanpham_id}}" style="border: none; background: white; cursor: pointer;"><i class="far fa-edit" style="color: green;"></i></button> ' +
    '<button class="delete" sp-id="{{sanpham_id}}" style="border: none; background: white; cursor: pointer;"><i class="fas fa-trash" style="color: red;"></i></button>' +
    '</td>' +
    "</tr>" +
    "{{/each}}";

var rowDtHbs = Handlebars.compile(rowDanhSachDT);

var generalSelect = '<div class="select-group">' +
    '<br/>' +
    '<label for="{{name}}">{{propName}}</label>' +
    '<select name="{{name}}" id="{{name}}" class="prop"> {{#each data}}' +
    '<option value="{{id}}">{{name}}</option>' +
    '{{/each}}</select>' +
    '</div>';

var generalSelectHbs = Handlebars.compile(generalSelect);

function loadDanhSachDT() {
    var searchForm = FormDataJson.formToJson(document.querySelector("#searchFormTB")); // nếu nhiều form thì chuyển nó thành id
    var searchOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(searchForm)
    };

    fetch("/api/sanphamdt/search", searchOption)
        .then(res => res.json())
        .then(data => {
            var rowDTs = rowDtHbs({
                dienthoai: data.content
            });
            $("#tableSanPham").html(rowDTs);

            var pageHTML = "";
            for (var i = 0; i < data.totalPages; i++) {
                pageHTML += `<button value="${i}" class="page-link pageIndex">${i+1}</button>`
            }
            $("#phanTrang").html(pageHTML);

            $(".pageIndex").click(function() {
                $('#searchFormTB input[name="trang"]').val($(this).val());
                loadDanhSachDT();
            });
            exitModal();
            bindClickEdit();
            bindEventDelete();
        });
}
loadDanhSachDT();

// danh sách url select

//Show model
var insertDTModal = new bootstrap.Modal(document.getElementById('editDTModal'), {
    keyboard: false
});

////////////////////////THÊM ĐIỆN THOẠI///////////////////////////////////
// Chức năng thêm
$("#themDT").click(function() {
    insertDTModal.show();
    fetch("/api/sanphamdt/allproperties")
        .then(res => res.json())
        .then(properties => {
            let selects = "";
            for (let property of properties) {
                selects += generalSelectHbs(property);
            }
            var formAdd = '<div class="them-sp-ndthaotac">' +
                '<div class="them-sanpham-trai">' +
                '<label for="sanpham_name" class="the-ten-tm">Tên sản phẩm</label>' +
                '<br/>' +
                '<input id="sanpham_name" name="sanpham_name" class="the-input-tm">' +
                '<br/>' +
                '<div class="selects-group-product">';
            formAdd += selects;
            formAdd +=
                '</div>' +
                '<div class="thong-tin-tm-323">' +
                '<div class="tm-anh-1">' +
                '<label for="sanpham_anh1" class="the-ten-tm-anh1">Link ảnh 1</label>' +
                '<input id="sanpham_anh1" name="sanpham_anh1" class="the-input-tm-anh1">' +
                '<label for="sanpham_anh2" class="the-ten-tm-anh">Link ảnh 2</label>' +
                '<input id="sanpham_anh2" name="sanpham_anh2" class="the-input-tm-anh1">' +
                '</div>' +
                '</div>' +
                '<div class="thong-tin-tm-333">' +
                '<div class="tm-tt1">' +
                '<label for="sanpham_giaban" class="the-ten-tm-3">Giá bán</label>' +
                '<br/>' +
                '<input type="number" id="sanpham_giaban" name="sanpham_giaban" class="the-input-tm-3-1 mglef10">' +
                '<br/>' +
                '</div>' +
                '<div class="tm-tt2">' +
                '<label for="sanpham_soluong" class="the-ten-tm-3">Số lượng</label>' +
                '<br/>' +
                '<input type="number" name="sanpham_soluong" id="sanpham_soluong" class="the-input-tm-3">' +
                '<br/>' +
                '</div>' +
                '</div>' +
                '<div class="them-sanpham-duoi">' +
                '<br>' +
                '<label for="sanpham_url" class="the-ten-tm">URL sản phẩm</label>' +
                '<br/>' +
                '<input id="sanpham_url" name="sanpham_url" class="the-input-tm">' +
                '<br/>' +
                '</div>' +
                '<div class="thong-tin-tm-334">' +
                '<br>' +
                '<div class="mota-1">' +
                '<label for="sanpham_mota" class="the-ten-tm">Mô tả sản phẩm</label>' +
                '<br/>' +
                '<textarea id="sanpham_mota" name="sanpham_mota"  class="noidung-mota1" cols="15" rows="5"></textarea>' +
                '<br/>' +
                '</div>' +
                '<div class="lienhe-11">' +
                '<label for="sanpham_lienhe" class="the-ten-tm">Liên hệ mua hàng</label>' +
                '<br/>' +
                '<input id="sanpham_lienhe" name="sanpham_lienhe"  class="noidung-lienhe1">' +
                '<br/>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            $("#editDTForm").html(formAdd);
        });

});

$("#save").click(function() {
    var dienThoai = FormDataJson.formToJson(document.querySelector("form"));
    var selectProps = $(".prop");
    for (let i = 0; i < selectProps.length; i++) {
        let select = $(selectProps[i]);
        let name = select.attr("name");
        let names = name.split(".");
        dienThoai[names[0]] = {};
        dienThoai[names[0]][names[1]] = select.val();
    }
    var insertOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dienThoai)

    };
    dienThoai = fetch(url, insertOption)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            loadDanhSachDT();
        })
    alert("Thêm vào thành công!");
    insertDTModal.hide();
});

/////////////////////////////DELETE////////////////////////////

function bindEventDelete() {
    $(".delete").click(function() {
        deleteSanPham($(this));
    })
}

function deleteSanPham($button) {
    var id = $button.attr("sp-id");
    alert("Xác nhận xóa sản phẩm có ID = " + id);
    var deleteOption = {
        method: "delete",
        headers: {
            'Content-Type': 'application/json'
        }
    };
    fetch(url + "/" + id, deleteOption)
        .then(response => {
            loadDanhSachDT();
        });
}

//////////////////////////////THOÁT////////////////////////////////
function exitModal() {
    $("#close").click(function() {
        alert("Xác nhận thoát!")
        insertDTModal.hide()
    })
}

////////////////////////TÌM KIẾM///////////////////////////////////
$("#searchBtn").click(function() {
    loadDanhSachDT();
});

////////////////////////CHỨC NĂNG SỬA///////////////////////////////////


function bindClickEdit() {
    $(".edit").click(function() {
        insertDTModal.show();
        fetch("/api/sanphamdt/allproperties")
            .then(res => res.json())
            .then(properties => {
                let selects = "";
                for (let property of properties) {
                    selects += generalSelectHbs(property);
                }

                var id = $(this).attr("sanpham_id");

                fetch(url + "/" + id)
                    .then(res => res.json())
                    .then(data => {
                        var formAdd = '<input type="text" name="sanpham_id" id-sanpham="sanpham_id" value = "{{sanpham_id}}">' +
                            '<div class="them-sp-ndthaotac">' +
                            '<div class="them-sanpham-trai">' +
                            '<label for="sanpham_name" class="the-ten-tm">Tên sản phẩm</label>' +
                            '<br/>' +
                            '<input id="sanpham_name" name="sanpham_name" value="{{sanpham_name}}"  class="the-input-tm">' +
                            '<br/>' +
                            '<div class="selects-group-product">';
                        formAdd += selects;
                        formAdd +=
                            '</div>' +
                            '<div class="thong-tin-tm-323">' +
                            '<div class="tm-anh-1">' +
                            '<label for="sanpham_anh1" class="the-ten-tm-anh1">Link ảnh 1</label>' +
                            '<input id="sanpham_anh1" name="sanpham_anh1" value="{{sanpham_anh1}}" class="the-input-tm-anh1">' +
                            '<label for="sanpham_anh2" class="the-ten-tm-anh">Link ảnh 2</label>' +
                            '<input id="sanpham_anh2" name="sanpham_anh2" value="{{sanpham_anh2}}" class="the-input-tm-anh1">' +
                            '</div>' +
                            '</div>' +
                            '<div class="thong-tin-tm-333">' +
                            '<div class="tm-tt1">' +
                            '<label for="sanpham_giaban" class="the-ten-tm-3">Giá bán</label>' +
                            '<br/>' +
                            '<input type="number" id="sanpham_giaban" name="sanpham_giaban" value="{{sanpham_giaban}}" class="the-input-tm-3-1 mglef10">' +
                            '<br/>' +
                            '</div>' +
                            '<div class="tm-tt2">' +
                            '<label for="sanpham_soluong" class="the-ten-tm-3">Số lượng</label>' +
                            '<br/>' +
                            '<input type="number" name="sanpham_soluong" id="sanpham_soluong" value="{{sanpham_soluong}}" class="the-input-tm-3">' +
                            '<br/>' +
                            '</div>' +
                            '</div>' +
                            '<div class="them-sanpham-duoi">' +
                            '<br>' +
                            '<label for="sanpham_url" class="the-ten-tm">URL sản phẩm</label>' +
                            '<br/>' +
                            '<input id="sanpham_url" value="{{sanpham_url}}" name="sanpham_url" class="the-input-tm">' +
                            '<br/>' +
                            '</div>' +
                            '<div class="thong-tin-tm-334">' +
                            '<br>' +
                            '<div class="mota-1">' +
                            '<label for="sanpham_mota" class="the-ten-tm">Mô tả sản phẩm</label>' +
                            '<br/>' +
                            '<textarea id="sanpham_mota" name="sanpham_mota"  class="noidung-mota1" cols="15" rows="5">{{sanpham_mota}}</textarea>' +
                            '<br/>' +
                            '</div>' +
                            '<div class="lienhe-11">' +
                            '<label for="sanpham_lienhe" class="the-ten-tm">Liên hệ mua hàng</label>' +
                            '<br/>' +
                            '<input id="sanpham_lienhe" name="sanpham_lienhe" value="{{sanpham_lienhe}}"  class="noidung-lienhe1">' +
                            '<br/>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>';
                        var editSanPham = Handlebars.compile(formAdd);
                        var editHtml = editSanPham(data)
                        $("#editDTForm").html(editHtml);
                    })

            });

    });
}

$("#save").click(function() {
    var sanPham = FormDataJson.formToJson(document.querySelector("form"));
    var id = $(this).attr("id-sanpham");
    var editOption = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(sanPham)

    };

    sanPham = fetch(url + "/" + id, editOption)
        .then(response => response.json())
        .then(data => {
            loadDanhSachDT();
        })
    insertDTModal.hide();

})