const API_BASE = "https://smart-appointment-cj4l.onrender.com";

const form = document.getElementById("loginForm");
const message = document.getElementById("message");
const submitButton = document.getElementById("submitButton");

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;

    message.textContent = "Connecting to server...";
    message.className = "";
    submitButton.disabled = true;
    submitButton.textContent = "Signing in...";

    try {
        const response = await fetch(`${API_BASE}/api/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error(
                result.message || "Invalid username or password."
            );
        }

        message.textContent =
            result.message || "Login successful! Welcome back.";

        message.className = "success";

        sessionStorage.setItem(
            "hospitalUser",
            JSON.stringify({
                username: result.username || username,
                role: result.role
            })
        );

        setTimeout(function () {
            window.location.href = "dashboard.html";
        }, 1000);

    } catch (error) {
        console.error("Login error:", error);

        if (error instanceof TypeError) {
            message.textContent =
                "Unable to connect to the server. Please try again.";
        } else {
            message.textContent = error.message;
        }

        message.className = "error";

    } finally {
        submitButton.disabled = false;
        submitButton.textContent = "Sign in";
    }
});