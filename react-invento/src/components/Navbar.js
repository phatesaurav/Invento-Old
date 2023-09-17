import { Link } from "react-router-dom";

function Navbar() {
  return (
    <div>
      <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <a class="navbar-brand" href="#" style={{ marginLeft: "20px" }}>
          {" "}
          {/* Give left margin of 20px */}
          Product Management System
        </a>
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active"><Link to="/" class="nav-link" href="#">Home</Link></li>
            <li class="nav-item active"><Link to="/addProduct" class="nav-link" href="#">Add Product</Link></li>
          </ul>
        </div>
      </nav>
    </div>
  );
}

export default Navbar;
