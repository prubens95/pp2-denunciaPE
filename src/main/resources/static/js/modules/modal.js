export default class Modal {
  constructor(modal, link, close) {
    this.modal = document.querySelector(modal);
    this.link = document.querySelector(link);
    this.close = document.querySelector(close);
    this.abrir = this.abrir.bind(this);
    this.fechar = this.fechar.bind(this);
  }

  abrir(event) {
    event.preventDefault();
    this.modal.classList.add("ativo");
  }

  fechar({ target }) {
    if (target === this.modal || target === this.close) {
      this.modal.classList.remove("ativo");
    }
  }

  addEvent() {
    this.link.addEventListener("click", this.abrir);
    this.close.addEventListener("click", this.fechar);
    window.addEventListener("click", this.fechar);
  }

  init() {
    this.addEvent();
    return this;
  }
}
