$.validator.prototype.checkForm = function () {
    this.prepareForm();
    for (var i = 0, elements = (this.currentElements = this.elements()); elements[i]; i++) {
        if (this.findByName(elements[i].name).length != undefined && this.findByName(elements[i].name).length > 1) {
            for (var cnt = 0; cnt < this.findByName(elements[i].name).length; cnt++) {
                this.check(this.findByName(elements[i].name)[cnt]);
            }
        } else {
            this.check(elements[i]);
        }
    }
    return this.valid();
}

$.validator.addMethod("singleFile", function (value, element) {
    return this.optional(element) || element.files.length === 1;
}, "Please select only one file.");

$.validator.addMethod("filetype", function (value, element, param) {
    // param is the allowed file types (e.g. "jpg|jpeg|png|gif")
    var fileType = param.split("|");
    var file = element.files[0];
    if (file) {
        var extension = file.name.split('.').pop().toLowerCase();
        return $.inArray(extension, fileType) !== -1;
    }
    return false;
}, "Invalid file type. Only {0} files are allowed.");