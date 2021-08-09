var start = 0;
var danhSachSliderItem = document.getElementsByName('slider-item');
hienThiSliderHienTai(start);

loadChamTron();
let chayauto = true;
// them su kien hover
let nodeSlider = document.getElementById('slider');

nodeSlider.addEventListener('mouseover', () => {
    chayauto = !chayauto;
});
nodeSlider.addEventListener('mouseout', () => {
    chayauto = !chayauto;
})

setInterval(autoChuyenHinh, 3000); // thời gian tính bằng milisecond, ví dụ : 1000 tương đương 1 giây
function autoChuyenHinh() {
    if (chayauto)
        onClickXemAnhSau();
}

function onClickXemAnhTruoc() {
    start--;
    if (start == -1) {
        start = danhSachSliderItem.length - 1;
    }
    hienThiSliderHienTai(start);
}

function onClickXemAnhSau() {
    start++;
    if (start == danhSachSliderItem.length) {
        start = 0;
    }
    hienThiSliderHienTai(start);
}

function hienThiSliderHienTai(viTri) {
    for (var i = 0; i < danhSachSliderItem.length; i++) {
        danhSachSliderItem[i].style.display = 'none';
    }
    danhSachSliderItem[viTri].style.display = 'block';
}
/** */

function loadChamTron() {
    let nodeCham = document.getElementById('khungChamTron');

    for (let i = 0; i < danhSachSliderItem.length; i++) {
        let nodeChamTron = document.createElement('div');
        nodeChamTron.className = 'nut-cham-tron';
        nodeChamTron.addEventListener('click', () => {
            hienThiSliderHienTai(i);
        });
        nodeCham.append(nodeChamTron);
    }
}