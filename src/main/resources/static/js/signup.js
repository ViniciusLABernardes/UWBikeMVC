document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.querySelector('section');
    signupForm.style.opacity = 0;

    setTimeout(() => {
        signupForm.style.transition = 'opacity 1s ease-in-out';
        signupForm.style.opacity = 1;
    }, 500);

    const signupButton = document.querySelector('button');
    signupButton.addEventListener('click', function () {
        const login = document.querySelector('input[type="username"]');
        const senha = document.querySelector('input[type="password"]');
        const confSenha = document.querySelector('input[type="password"][name="senhacon"]');

        const isValid = login.checkValidity() && senha.checkValidity() && confSenha.checkValidity();

        if (!isValid) {
            signupForm.classList.add('shake');

            setTimeout(() => {
                signupForm.classList.remove('shake');
            }, 1000);
        }
    });
});
