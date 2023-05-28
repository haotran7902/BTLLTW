//Xử lý hành động khi click chọn ảnh
function previewFile() {
    var preview = document.querySelector('#preview');
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
  
    reader.onloadend = function() {
      preview.src = reader.result;
      document.getElementById('src').value = reader.result;
    }
  
    if (file) {
      reader.readAsDataURL(file);
    } else {
      preview.src = "";
    }
  }
  
// Xử lý hành động khi click vào nút Edit
const editBtn = document.querySelector('.view_button > .edit')
const saveBtn = document.querySelector('.view_button > .save')
const addBtn = document.querySelector('.view_button > .add')

const viewInputs = document.querySelectorAll('.view_input')
const viewSelect = document.querySelector('.view_select')
const viewArea = document.querySelector('.view_textarea')

editBtn.onclick = function() {
    editBtn.classList.remove('active');
    saveBtn.classList.add('active')

    for (var i = 0; i < viewInputs.length; i++) {
        viewInputs[i].disabled = false;
      
    }
    viewSelect.disabled = false;
    viewArea.disabled = false;
}