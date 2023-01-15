import Modal from "./modules/Modal.js";
import Cep from "./modules/Cep.js";

const contato = new Modal(".contato", "#contato", ".close img");
contato.init();

const cep = new Cep('input[id="cep"]', ".erro", "#bairro", "#rua");
cep.init();
