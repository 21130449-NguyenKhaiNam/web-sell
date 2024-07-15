import {endLoading, http, startLoading} from "./base.js";

export const uploadImage = function (fileUploads, automaticLoading = true) {
    return new Promise((resolve, reject) => {
        const numberOfFiles = fileUploads.length;
        const dataReturn = [];
        http({
            url: '/api/admin/generateSignature',
            method: 'POST',
            data: {
                numberOfFiles: numberOfFiles,
                publicId: fileUploads.map(fileUpload => fileUpload.name),
                folder: fileUploads.map(fileUpload => fileUpload.folder)
            },
        }).then(async (response) => {
            const apiKey = response.api_key;
            const cloudName = response.cloud_name;
            const signs = response.signs;
            const uploadPromises = [];
            for (let i = 0; i < numberOfFiles; i++) {
                const formData = new FormData();
                formData.append('api_key', apiKey);
                formData.append("folder", fileUploads[i].folder);
                formData.append('file', fileUploads[i].file);
                console.log('file', fileUploads[i].file)
                formData.append("public_id", fileUploads[i].name);
                formData.append('timestamp', signs[i].timestamp);
                formData.append('signature', signs[i].signature);

                uploadPromises.push(
                    fetch(`https://api.cloudinary.com/v1_1/${cloudName}/auto/upload`, {
                        method: 'POST',
                        body: formData
                    })
                        .then(response => {
                            console.log("success")
                            return response.json()
                        })
                        .then(data => dataReturn[i] = {
                            url: data.url,
                            public_id: data.public_id,
                            format: data.format
                        })
                        .catch(error => dataReturn[i] = "error")
                );
            }
            try {
                if (automaticLoading)
                    startLoading();
                await Promise.all(uploadPromises);
                if (automaticLoading)
                    endLoading();
                resolve(dataReturn);
            } catch (error) {
                console.error('Error uploading images:', error);
                if (automaticLoading)
                    endLoading();
                reject(error);
            }
        }).catch(error => {
            console.error('Error generating signature:', error);
            reject(error);
        });
    });
}

export const deleteImage = function (fileDeletes, callback) {
    const numberOfFiles = fileDeletes.length;
    const urls = [];
    $.ajax({
        url: '/api/admin/generateSignature',
        method: 'POST',
        data: {
            numberOfFiles: numberOfFiles,
            publicId: fileDeletes.map(fileDelete => fileDelete.name),
            folder: fileDeletes.map(fileDelete => fileDelete.folder)
        },
        success: async function (response) {
            const apiKey = response.api_key;
            const cloudName = response.cloud_name;
            const signs = response.signs;
            const uploadPromises = [];
            for (let i = 0; i < numberOfFiles; i++) {
                const formData = new FormData();
                formData.append('api_key', apiKey);
                formData.append("public_id", fileDeletes[i].name);
                formData.append('timestamp', signs[i].timestamp);
                formData.append('signature', signs[i].signature);

                uploadPromises.push(
                    fetch(`https://api.cloudinary.com/v1_1/${cloudName}/image/destroy`, {
                        method: 'POST',
                        body: formData
                    })
                        .then(response => {
                            console.log("success")
                            return response.json()
                        })
                        .then(data => urls[i] = data.result)
                        .catch(error => urls[i] = "error")
                );
            }
            try {
                await Promise.all(uploadPromises);
            } catch (error) {
                console.error('Error uploading images:', error);
            } finally {
                callback(urls);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error('Error generating signature: ' + textStatus);
        }
    })
};

const CLOUDNAME = "yourstyle";

export function getImageProduct(nameImages) {
    return `https://res.cloudinary.com/${CLOUDNAME}/image/upload/c_scale/q_auto/f_webp,fl_awebp/v1711545951/product_img/${nameImages}`;
}

export function fetchImage(url) {
    return fetch(url).then(response => response.blob()).catch(error => console.error('Error fetching image:', error));
}
