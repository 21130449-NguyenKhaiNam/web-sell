<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--filepond-->
<link href="https://unpkg.com/filepond/dist/filepond.css" rel="stylesheet"/>
<link
        href="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css"
        rel="stylesheet"/>

<!-- include FilePond plugins -->
<script src="https://unpkg.com/filepond/dist/filepond.min.js"></script>
<script src="https://unpkg.com/filepond-plugin-image-validate-size/dist/filepond-plugin-image-validate-size.js"></script>
<script src="https://unpkg.com/filepond-plugin-file-validate-type/dist/filepond-plugin-file-validate-type.js"></script>
<script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.min.js"></script>
<script src="https://unpkg.com/filepond-plugin-file-rename/dist/filepond-plugin-file-rename.js"></script>
<script src="https://unpkg.com/jquery-filepond/filepond.jquery.js"></script>
<script>
    $(document).ready(function () {
        const sizeLimit = 5 * 1024 * 1024; // 5MB in bytes
        const fileTypeAccept = ['jpeg', 'png', 'jpg'];
        FilePond.registerPlugin(
            FilePondPluginImagePreview,
            FilePondPluginFileRename,
        );
        FilePond.setOptions({
            onaddfilestart: function (file) {
                if (file.fileSize > sizeLimit) {
                    handleFileError('File quá lớn, tối đa là 5MB');
                    file.abortLoad();
                    return false
                }
                if (!(fileTypeAccept.includes(file.fileExtension.toLowerCase()))) {
                    handleFileError(`Chỉ chấp nhận các file đuôi dạng: ${fileTypeAccept.join(', ')}`);
                    file.abortLoad();
                    return false
                }
                return true;
            },
            onprocessfile:
                function (error, file) {
                    if (!error) {
                        console.log('File added to FilePond', file);
                    } else {
                        console.error('File not added to FilePond', error);
                    }
                },
            onerror: function (error) {
                console.log("onerror")
                console.error('FilePond error:', error);
            }
        })

        function handleFileError(message) {
            Swal.fire({
                icon: 'error',
                title: 'Lỗi thêm ảnh',
                text: message
            });
        }
    })
</script>