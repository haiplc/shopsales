var urlSanPham = "/api/sanphamdt";
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

// var hangSelect = '<select id="hang.hang_id">{{#each hangdts}}' +
//     '<option value="{{hang_id}}>' +
//     '"{{hang_name}}' +
//     '</option>' +

function loadDanhSachDT() {
    fetch(urlSanPham)
        .then(res => res.json())
        .then(datas => {
            var rowDTs = rowDtHbs({
                dienthoai: datas
            });
            $("#tableSanPham").html(rowDTs);
            exitModal();
        });
}
loadDanhSachDT();

// danh sách url select
var urlMauSac = "/api/mausacdt";
var urlHang = "/api/hangdt";
var urlChip = "/api/chipdt";
var urlRam = "/api/ramdt";
var urlManHinh = "/api/manhinhdt";
var urlBoNho = "/api/bonhodt";
var urlLoai = "/api/loaidt";

//Show model
var insertDTModal = new bootstrap.Modal(document.getElementById('editDTModal'), {
    keyboard: false
});

// Chức năng thêm
$("#themDT").click(function() {
    insertDTModal.show();
    var formAdd =
        '<div class="them-sp-ndthaotac">' +
        '<div class="them-sanpham-trai">' +
        '<label th:field="*{sanpham_name}" class="the-ten-tm">[[#{lo.index.tensp}]]</label>' +
        '<br/>' +
        '<input th:field="*{sanpham_name}" class="the-input-tm">' +
        '<br/>' +
        '<i th:errors="*{sanpham_name}" style="color:red; margin-left: 30px; font-style: italic; position: absolute;  margin-top: 5px;"></i>' +
        ' <br/>' +
        '<div class="selects-group-product">' +
        '<div class="select-group">' +
        '<label th:field="*{hang}">[[#{lo.index.hangdtss}]]</label>' +
        '<select th:field="*{hang}">' +
        '</select>' +
        '</div>' +
        '<div class="selects-group-product">' +
        '<div class="select-group">' +
        '<label th:field="*{hang}">[[#{lo.index.hangdtss}]]</label>' +
        '<select th:field="*{hang}">' +
        '</select>' +
        '</div>' +
        '<div class="selects-group-product">' +
        '<div class="select-group">' +
        '<label th:field="*{hang}">[[#{lo.index.hangdtss}]]</label>' +
        '<select th:field="*{hang}">' +
        '</select>' +
        '</div>' +
        '<div class="selects-group-product">' +
        '<div class="select-group">' +
        '<label th:field="*{hang}">[[#{lo.index.hangdtss}]]</label>' +
        '<select th:field="*{hang}">' +
        '</select>' +
        '</div>' +
        '<div class="selects-group-product">' +
        '<div class="select-group">' +
        '<label th:field="*{hang}">[[#{lo.index.hangdtss}]]</label>' +
        '<select th:field="*{hang}">' +
        '</select>' +
        '</div>' +
        '<div class="selects-group-product">' +
        '<div class="select-group">' +
        '<label th:field="*{hang}">[[#{lo.index.hangdtss}]]</label>' +
        '<select th:field="*{hang}">' +
        '</select>' +
        '</div>' +
        '<div class="selects-group-product">' +
        '<div class="select-group">' +
        '<label th:field="*{hang}">[[#{lo.index.hangdtss}]]</label>' +
        '<select th:field="*{hang}">' +
        '</select>' +
        '</div>' +
        '</div>' +
        '<div class="thong-tin-tm-3">' +
        '<div class="tm-tt1">' +
        '<label th:field="*{sanpham_giaban}" class="the-ten-tm-3">Giá bán</label>' +
        ' <br/>' +
        ' <input type="number" th:field="*{sanpham_giaban}" class="the-input-tm-3-1">' +
        ' <br/>' +
        '</div>' +
        ' <input type="hidden" th:field="*{sanpham_giaban}" class="the-input-tm-3-1">' +
        '<div class="tm-tt1">' +
        '<label th:field="*{sanpham_giaban}" class="the-ten-tm-3">Giá bán</label>' +
        ' <br/>' +
        ' <input type="number" th:field="*{sanpham_giaban}" class="the-inputid">' +
        ' <br/>' +
        '</div>' +
        '</div>' +
        ' <br/>' +
        '<label th:field="*{sanpham_url}" class="the-ten-tm">URL sản phẩm</label>' +
        ' <br/>' +
        '<input th:field="*{sanpham_url}" class="the-input-tm">' +
        ' <br/>' +
        '</div>' +
        '<div class="them-sanpham-phai">' +
        '<div class="tm-anh-1">' +
        ' <br/>' +
        '<label th:field="*{sanpham_anh1}" class="the-ten-tm-anh">Link ảnh 1</label>' +
        '<br/>' +
        '<input th:field="*{sanpham_anh1}" class="the-input-tm-anh">' +
        '<br/>' +
        '<br/>' +
        '<label th:field="*{sanpham_anh2}" class="the-ten-tm-anh">Link ảnh 2</label>' +
        '<br/>' +
        '<input th:field="*{sanpham_anh2}" class="the-input-tm-anh">' +
        '<br/>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="them-sp-ndthaotac rut-xuong">' +
        '<div class="mota-1">' +
        '<label th:field="*{sanpham_mota}" class="the-ten-tm">Mô tả sản phẩm</label>' +
        '<br/>' +
        '<textarea th:field="*{sanpham_mota}" class="noidung-mota" cols="30" rows="10"></textarea>' +
        '<br/>' +
        '</div>' +
        '<div class="lienhe-1">' +
        '<label th:field="*{sanpham_lienhe}" class="the-ten-tm">Liên hệ mua hàng</label>' +
        '<br/>' +
        '<textarea th:field="*{sanpham_lienhe}" class="noidung-lienhe" cols="30" rows="10"></textarea>' +
        '<br/>' +
        '</div>' +
        '</div>';
    // thêm nội dung vào modal
    $("#editDTForm").html(formAdd);
});



function exitModal() {
    $("#close").click(function() {
        alert("Xác nhận thoát!")
        insertDTModal.hide()
    })
}