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