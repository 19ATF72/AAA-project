<!doctype html>
<html lang="en">
<%@include file='/html/scripts.html'%>
<style><%@include file="/css/style.css"%></style>
    
<body>
  <%@include file='/html/header.html'%>

  <main class="container">
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      <i class="fas fa-laptop-medical me-1" style="font-size:90px"></i>
      <h1 class="display-4">SmartCare</h1>
      <p class="lead">Quickly build an effective pricing table for your potential customers with this Bootstrap example. It?s built with default Bootstrap components and utilities with little customization.</p>
    </div>

    <div class="row row-cols-1 row-cols-md-3 mb-3 text-center">
      <div class="col patient-col">
        <div class="card mb-4 shadow-sm">
          <div class="card-header">
            <h4 class="my-0 fw-normal">Patient login</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title patient-card-title">$15 <small class="text-muted">/ 10m slot</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>Book appointments online</li>
              <li>Cancel appointments</li>
              <li>Pay your bills</li>
              <li>Help center access</li>
            </ul>
            <button type="button" class="w-100 btn btn-lg btn-outline-primary">Login here</button>
          </div>
        </div>
      </div>
      <div class="col staff-col">
        <div class="card mb-4 shadow-sm">
          <div class="card-header">
            <h4 class="my-0 fw-normal">Staff portal</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title staff-card-title">$variable <small class="text-muted">/ 10m slot</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>View your schedule</li>
              <li>Write up appointments</li>
              <li>Prescribe drugs</li>
              <li>Help center access</li>
            </ul>
            <button type="button" class="w-100 btn btn-lg btn-outline-primary">Access portal</button>
          </div>
        </div>
      </div>
      <div class="col admin-col">
        <div class="card mb-4 shadow-sm">
          <div class="card-header">
            <h4 class="my-0 fw-normal">Admin area</h4>
          </div>
          <div class="card-body">
            <h1 class="card-title admin-card-title">$0 <small class="text-muted">/ ever</small></h1>
            <ul class="list-unstyled mt-3 mb-4">
              <li>Control everything</li>
              <li>Remove appointments</li>
              <li>Approve users</li>
              <li>Be the help center</li>
            </ul>
            <button type="button" class="w-100 btn btn-lg btn-outline-primary">Get access</button>
          </div>
        </div>
      </div>
    </div>

    <%@include file='/html/footer.html'%>
  </main>
</body>
</html>
