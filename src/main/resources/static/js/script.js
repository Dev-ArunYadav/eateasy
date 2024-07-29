document.addEventListener('DOMContentLoaded', function() {
  const carousel = document.querySelector('.brand-carousel');
  const leftBtn = document.querySelector('.left-btn');
  const rightBtn = document.querySelector('.right-btn');
  const scrollAmount = 300; // Adjust this value to control scroll distance

  leftBtn.addEventListener('click', () => {
    carousel.scrollBy({
      left: -scrollAmount,
      behavior: 'smooth'
    });
  });

  rightBtn.addEventListener('click', () => {
    carousel.scrollBy({
      left: scrollAmount,
      behavior: 'smooth'
    });
  });

  // Hide left button initially
  carousel.addEventListener('scroll', () => {
    leftBtn.style.display = carousel.scrollLeft > 0 ? 'block' : 'none';
    rightBtn.style.display =
      carousel.scrollLeft < carousel.scrollWidth - carousel.clientWidth ? 'block' : 'none';
  });
});



// Model PopUp
document.addEventListener('DOMContentLoaded', function() {
  const loginModal = document.getElementById('loginModal');
  const otpModal = document.getElementById('otpModal');
  const loginLink = document.getElementById('loginLink');
  const loginBtn = document.querySelector('.continue-btn');
  const closeBtns = document.getElementsByClassName('close');
  const backBtn = document.querySelector('.back-btn');

  // Show login modal when login link is clicked
  loginLink.onclick = function(e) {
    e.preventDefault();
    loginModal.style.display = 'block';
  }

  // Show OTP modal
  loginBtn.onclick = function() {
    loginModal.style.display = 'none';
    otpModal.style.display = 'block';
    startCountdown();
  }

  // Back button functionality
  backBtn.onclick = function() {
    otpModal.style.display = 'none';
    loginModal.style.display = 'block';
  }

  // Close modals
  for (let closeBtn of closeBtns) {
    closeBtn.onclick = function() {
      loginModal.style.display = 'none';
      otpModal.style.display = 'none';
    }
  }

  // Close modals when clicking outside
  window.onclick = function(event) {
    if (event.target == loginModal) {
      loginModal.style.display = 'none';
    }
    if (event.target == otpModal) {
      otpModal.style.display = 'none';
    }
  }

  // OTP input handling
  const otpInputs = document.querySelectorAll('.otp-input-group input');

  otpInputs.forEach((input, index) => {
    input.addEventListener('input', function() {
      if (this.value.length === this.maxLength) {
        if (index < otpInputs.length - 1) {
          otpInputs[index + 1].focus();
        }
      }
    });

    function verifyOtp() {
        const otp = Array.from(otpInputs).map(input => input.value).join('');
        const mobile = document.getElementById('hiddenMobileNumber').value;

        var Payload = {
            mobileNumber: mobile,
        };

        fetch('/santacruz/u1/auth/verify-otp?otp='+ otp,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Payload)
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('OTP verified successfully!');
                loginModal.style.display = 'none';
                otpModal.style.display = 'none';
            } else {
                alert('Invalid OTP. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred. Please try again later.');
        });
    }
    input.addEventListener('keydown', function(e) {
      if (e.key === 'Backspace' && this.value.length === 0) {
        if (index > 0) {
          otpInputs[index - 1].focus();
        }
      }
    });
  });

  // OTP countdown
  function startCountdown() {
    let countdown = 29;
    const countdownElement = document.getElementById('countdown');
    const intervalId = setInterval(function() {
      countdownElement.textContent = countdown;
      countdown--;
      if (countdown < 0) {
        clearInterval(intervalId);
        countdownElement.textContent = '0';
      }
    }, 1000);
  }
});

loginLink.onclick = function(e) {
  e.preventDefault(); // Prevent the default link behavior
  loginModal.style.display = 'block';
}

backBtn.onclick = function() {
  otpModal.style.display = 'none';
  loginModal.style.display = 'block';
}
