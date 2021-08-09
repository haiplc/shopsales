function anChinhSua() {
	let nodeChinhSua = document.getElementById('dangNhap');
	nodeChinhSua.style.display = 'none';
}

function nodeChinhSua() {
	let nodeChinhSua = document.getElementById('dangNhap');
	nodeChinhSua.style.display = 'flex';
}

function anThemMoiSP() {
	let nodeChinhSua = document.getElementById('dangNhapsp');
	nodeChinhSua.style.display = 'none';
}

function nodeThemMoiSP() {
	let nodeChinhSua = document.getElementById('dangNhapsp');
	nodeChinhSua.style.display = 'flex';
}

var myModal = document.getElementById('myModal')
var myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', function() {
	myInput.focus()
})