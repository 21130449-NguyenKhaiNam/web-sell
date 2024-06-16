class Modal {
    constructor(modalId, {afterClose, beforeClose, afterOpen, beforeOpen}) {
        this.modal = document.getElementById(modalId);
        this.closeBtn = this.modal.querySelector('.close');
        this.overlay = this.modal.querySelector('.overlay');
        this.triggerOpen = document.querySelectorAll(`[modal-triger-open="${modalId}"]`);
        this.triggerClose = document.querySelectorAll(`[modal-triger-close="${modalId}"]`);

        this.afterClose = afterClose || function() {};
        this.beforeClose = beforeClose || function() {};
        this.afterOpen = afterOpen || function() {};
        this.beforeOpen = beforeOpen || function() {};
        this.closeBtn.addEventListener('click', this.close.bind(this));
        this.overlay.addEventListener('click', this.close.bind(this));
        this.triggerOpen.forEach((item)=>{
            item.addEventListener('click', this.open.bind(this));
        })
        this.triggerClose.forEach((item)=>{
            item.addEventListener('click', this.close.bind(this));
        })
    }

    open() {
        console.log("open")
        this.beforeOpen(this.modal);
        this.modal.style.display = 'block';
        this.afterOpen(this.modal);
    }

    close() {
        console.log("close")
        this.beforeClose(this.modal);
        this.modal.style.display = 'none';
        this.afterClose(this.modal);
    }

}
