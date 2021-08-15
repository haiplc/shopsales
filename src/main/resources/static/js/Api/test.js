// render Hang
var hangSelect = '<select name="hang.hang_id" id="hang.hang_id"> {{#each hangdts}}' +
    '<option value="{{hang_id}}">{{hang_name}}</option>' +
    '{{/each}}</select>';

var hangSelectHbs = Handlebars.compile(hangSelect);

// render Manhinh
var manHinhSelect = '<select name="manhinh.manhinh_id" id="manhinh.manhinh_id"> {{#each manhinhdts}}' +
    '<option value="{{manhinh_id}}">{{manhinh_name}}</option>' +
    '{{/each}}</select>';

var manHinhSelectHbs = Handlebars.compile(manHinhSelect);

// render Mausac
var mauSacSelect = '<select name="mausac.mausac_id" id="mausac.mausac_id"> {{#each mausacdts}}' +
    '<option value="{{mausac_id}}">{{mausac_name}}</option>' +
    '{{/each}}</select>';

var mauSacSelectHbs = Handlebars.compile(mauSacSelect);

// render CHIP
var chipSelect = '<select name="chip.chip_id" id="chip.chip_id"> {{#each chipdts}}' +
    '<option value="{{chip_id}}">{{chip_name}}</option>' +
    '{{/each}}</select>';

var chipSelectHbs = Handlebars.compile(chipSelect);

// render RAM
var ramSelect = '<select name="ram.ram_id" id="ram.ram_id"> {{#each ramdts}}' +
    '<option value="{{ram_id}}">{{ram_name}}</option>' +
    '{{/each}}</select>';

var ramSelectHbs = Handlebars.compile(ramSelect);

// render Bonho
var boNhoSelect = '<select id="bonho.bonho_id" name="bonho.bonho_id"> {{#each bonhodts}}' +
    '<option value="{{bonho_id}}">{{bonho_name}}</option>' +
    '{{/each}}</select>';

var boNhoSelectHbs = Handlebars.compile(boNhoSelect);

// render Loai
var loaiSelect = '<select name="loai.loai_id" id="loai.loai_id"> {{#each loaidts}}' +
    '<option value="{{loai_id}}">{{loai_name}}</option>' +
    '{{/each}}</select>';

var loaiSelectHbs = Handlebars.compile(loaiSelect);

// lấy danh mục hãng
fetch("/api/hangdt")
    .then(res => res.json())
    .then(data => {
        var hangSelectHtml = hangSelectHbs({ hangdts: data });

        fetch("/api/manhinhdt")
            .then(res => res.json())
            .then(data => {
                var manHinhSelectHtml = manHinhSelectHbs({ manhinhdts: data });

                fetch("/api/mausacdt")
                    .then(res => res.json())
                    .then(data => {
                        var mauSacSelectHtml = mauSacSelectHbs({ mausacdts: data });

                        fetch("/api/chipdt")
                            .then(res => res.json())
                            .then(data => {
                                var chipSelectHtml = chipSelectHbs({ chipdts: data });

                                fetch("/api/ramdt")
                                    .then(res => res.json())
                                    .then(data => {
                                        var ramSelectHtml = ramSelectHbs({ ramdts: data });

                                        fetch("/api/bonhodt")
                                            .then(res => res.json())
                                            .then(data => {
                                                var boNhoSelectHtml = boNhoSelectHbs({ bonhodts: data });

                                                fetch("/api/loaidt")
                                                    .then(res => res.json())
                                                    .then(data => {
                                                        var loaiSelectHtml = loaiSelectHbs({ loaidts: data });

                                                        var formAdd = '<div class="them-sp-ndthaotac">' +
                                                            '<div class="them-sanpham-trai">' +
                                                            '<label for="sanpham_name" class="the-ten-tm">Tên sản phẩm</label>' +
                                                            '<br/>' +
                                                            '<input id="sanpham_name" name="sanpham_name" class="the-input-tm">' +
                                                            '<br/>' +
                                                            '<div class="selects-group-product">';

                                                        formAdd += hangSelectHtml;

                                                        formAdd += manHinhSelectHtml;

                                                        formAdd += mauSacSelectHtml;

                                                        formAdd += chipSelectHtml;

                                                        formAdd += ramSelectHtml;

                                                        formAdd += boNhoSelectHtml;

                                                        formAdd += loaiSelectHtml;

                                                        formAdd += '</div>' +
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
                                                        // thêm nội dung vào modal
                                                        $("#editDTForm").html(formAdd);
                                                    });
                                            });
                                    });
                            });
                    });
            });
    });