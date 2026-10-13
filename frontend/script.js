<<<<<<< HEAD
const API_BASE = "http://localhost:8080";

=======
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
const form = document.getElementById("loginForm");
const message = document.getElementById("message");
const submitButton = document.getElementById("submitButton");

form.addEventListener("submit", async (event) => {
  event.preventDefault();
  message.textContent = "";
  message.className = "";
  submitButton.disabled = true;
  submitButton.textContent = "Signing in...";

  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value;

  try {
<<<<<<< HEAD
    const response = await fetch(`${API_BASE}/api/auth/login`, {
=======
    const response = await fetch("http://localhost:8080/api/auth/login", {
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password })
    });
    const result = await response.json();

    if (!response.ok) throw new Error(result.message || "Login failed.");
<<<<<<< HEAD

    sessionStorage.setItem("hospitalUser", JSON.stringify({ username: result.username, role: result.role }));
    message.textContent = result.message || "Login successful.";
    message.className = "success";
    setTimeout(() => { window.location.href = "dashboard.html"; }, 700);
=======
    message.textContent = result.message || "Login successful.";
    message.className = "success";
    // Replace this with your dashboard page when one is added.
    setTimeout(() => {
      window.location.href = "dashboard.html";
    }, 700);
>>>>>>> bcd2ce809b79020c4779aaa106071b5c2858f4c4
  } catch (error) {
    message.textContent = error.message.includes("Failed to fetch")
      ? "Cannot connect to backend. Start Spring Boot on port 8080."
      : error.message;
    message.className = "error";
  } finally {
    submitButton.disabled = false;
    submitButton.textContent = "Sign in";
  }
});
