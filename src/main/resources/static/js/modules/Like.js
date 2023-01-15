export default function initLike() {
  const like = document.querySelectorAll(".like")

  function handleClick({srcElement}) {
    if(srcElement.firstChild.getAttribute('src') !== "./img/megafone-fullfiled.svg") 
    return srcElement.firstChild.setAttribute('src', "./img/megafone-fullfiled.svg")
    else 
    return srcElement.firstChild.setAttribute('src', "./img/megafone-outline.svg")
  }

  like.forEach((item) => item.addEventListener('click', handleClick))
}