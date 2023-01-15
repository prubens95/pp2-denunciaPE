export default class Cep {
  constructor(input, erro, bairro, rua) {
    this.input = document.querySelector(input);
    this.erro = document.querySelector(erro);
    this.dados = { 
      bairro: document.querySelector(bairro),
      rua: document.querySelector(rua)
    };
    this.request = this.request.bind(this);
    this.validate = this.validate.bind(this);
  }

  validate({target}) {
    const regex = /^\d{5}-?\d{3}$/g;
    if(regex.test(target.value)) {
      this.success(target);
      this.request(target.value, target);
    } else {
      this.error(target);
    }
  }
  
  success(target) {
    this.erro.classList.remove("ativo");
    target.style.borderColor = "#00b181";
  }

  error(target, {bairro, rua} = this.dados) {
    bairro.value = null;
    rua.value = null;
    this.erro.classList.add("ativo");
    target.style.borderColor = "#da3128";
  }
  
  request(cep, target) {
    const {bairro, rua} = this.dados;
    fetch(`https://viacep.com.br/ws/${cep}/json/`)
    .then(response => response.json())
    .then(json => {

      bairro.value = json.bairro;
      rua.value = json.logradouro;

      if(json.erro) {
        bairro.value = null;
        rua.value = null;
        this.error(target);
      } else {
        this.success(target);
      }
    });
  }

  addEvent() {
    this.input.addEventListener("change", this.validate);
  }

  init() {
    if(this.input) {
      this.addEvent();
      return this;
    }
  }
}
