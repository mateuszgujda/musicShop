//http://jsfiddle.net/2xES5/28/

window.onload = function () {
    if(window.File && window.FileList && window.FileReader){
        $('#images').on("change", function(event){
            var files = event.target.files;
            var output = document.getElementById("result");
            for(var i=0 ; i<files.length; i++){
                var file = files[i];
                if(file.type.match("image")){
                    if(this.files[0].size<2097152){
                        var picReader = new FileReader();
                        picReader.addEventListener("load",function (ev) {
                            var picFile =  ev.target;
                            var div = document.createElement("div");
                            div.innerHTML = "<img class='img-thumbnail' src='" + picFile.result + "'"+ "title='preview/image' />";
                            output.insertBefore(div,null);
                        });
                        $("#result").show();
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
            }
        })
    } else {
     console.log("No image picker support");
    }
};


$('#images').on("click", function() {
    $('.img-thumbnail').parent().remove();
    $('#result').hide();
    $(this).val("");
    $("#clear").addClass("disabled");
});


$('#clear').on("click", function() {
    $('.thumbnail').parent().remove();
    $('#result').hide();
    $('#images').val("");
    $('#clear').addClass("disabled");
});
