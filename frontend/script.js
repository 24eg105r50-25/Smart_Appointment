const API_BASE = "https://smart-appointment-cj4l.onrender.com";

const form = document.getElementById("loginForm");
const message = document.getElementById("message");
const submitButton = document.getElementById("submitButton");

form.addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;

    if (!username || !password) {
        message.textContent = "Please enter your username and password.";
        message.className = "error";
        return;
    }

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

        console.log("Backend status:", response.status);
        console.log("Backend response:", result);

        if (!response.ok) {
            throw new Error(
                result.message || "Invalid username or password."
            );
        }

        // Save the logged-in user's details.
        sessionStorage.setItem(
            "hospitalUser",
            JSON.stringify({
                username: result.username || username,
                role: result.role || ""
            })
        );

        // Show the success message.
        message.textContent =
            result.message || "Login successful! Welcome back.";
        message.className = "success";

        // Open the dashboard after successful login.
        setTimeout(function () {
            window.location.href = "dashboard.html";
        }, 1000);

    } catch (error) {
        console.error("Login error:", error);

        if (error instanceof TypeError) {
            message.textContent =
                "Unable to connect to the server. Please try again.";
        } else {
            message.textContent =
                error.message || "Login failed. Please try again.";
        }

        message.className = "error";

    } finally {
        submitButton.disabled = false;
        submitButton.textContent = "Sign in";
    }
});