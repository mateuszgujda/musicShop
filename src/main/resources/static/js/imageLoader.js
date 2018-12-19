//http://jsfiddle.net/2xES5/28/

window.onload = function () {
    if(window.File && window.FileList && window.FileReader){
        $('#images').on("change", function(event){
            var files = event.target.files;
            var output = document.getElementById("result");
            var file = files[0];
                if(file.type.match("image")){
                    if(this.files[0].size<2097152){
                        var picReader = new FileReader();
                        picReader.addEventListener("load",function (ev) {
                            var picFile =  ev.target;
                            var imageHolder = $(imageHolder);
                            imageHolder.innerHTML = imageHolder.innerHTML + "<img class='img-thumbnail' src='" + picFile.result + "'"+ "title='preview/image' />";
                            output.insertBefore(div,null);
                        });
                        $("#clear").removeClass("disabled");
                        picReader.readAsDataURL(file);
                    } else{
                        alert("Image Size is too big. Minimum size is 2MB.");
                        $(this).val("");
                    }
                } else {
                    alert("You can only upload image file.");
                    $(this).val("");
                }
            })
    } else {
     console.log("No image picker support");
    }
};


$('#images').on("click", function() {
    $('#result').hide();
    $(this).val("");
    $("#add").addClass("disabled");
});


$('#add').on("click", function() {
    $('#images').val("");
    $('#add').addClass("disabled");
    var files = event.target.files;
    var file = files[0];



});
