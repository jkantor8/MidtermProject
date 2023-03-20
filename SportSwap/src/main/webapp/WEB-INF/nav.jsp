<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">SportSwap</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Create Listing
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="create_listing?listing_type=donation">Donation Listing</a></li>
            <li><a class="dropdown-item" href="create_listing?listing_type=swap">Swap Listing</a></li>
            <li><a class="dropdown-item" href="crate_listing?listing_type=sale">Sale Listing</a></li>
          </ul>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="listings.do">View Listings</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" href="#">Account</a>
        </li>
       
       
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>