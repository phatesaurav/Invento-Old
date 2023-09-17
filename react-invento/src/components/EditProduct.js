import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import base_url from "../api/BaseURL";
import { useEffect, useState } from "react";

function EditProduct() {
  const [product, setProduct] = useState({
    id: "",
    name: "",
    quantity: "",
    price: "",
  });

  const { id } = useParams(); // This "id" will come from App.js route path ":id"
  console.log(id);

  const [message, setMessage] = useState("");

  const navigate = useNavigate();

  useEffect(function () {
    document.title = "Edit";
    init();
  }, []);

  function init() {
    axios.get(`${base_url}/getProductById/${id}`).then(
      function (response) {
        console.log(response.data);
        setProduct(response.data);
      },
      function (error) {
        console.log(error);
      }
    );
  }

  function handleChange(event) {
    const value = event.target.value;
    setProduct({ ...product, [event.target.name]: value });
  }

  function handleSubmit(event) {
    event.preventDefault();
    console.log(product);
    axios.put(`${base_url}/updateProduct`, product).then(
      function (response) {
        console.log(response);
        setMessage("Product updated successfully!");
        setProduct({
          name: "",
          quantity: "",
          price: "",
        });
        setTimeout(() => {
          setMessage("");
        }, 3000);
        navigate("/");
      },
      function (error) {
        console.log(error);
        setMessage("Error updating product. Please try again.");
        setTimeout(() => {
          setMessage("");
        }, 3000);
      }
    );
  }

  return (
    <div className="container mt-3">
      {/* marginTop-3 */}
      <div className="row">
        <div className="col-md-6 offset-md-3">
          {/* On medium (md) and larger screens, the column will occupy 6 out of 12 available grid columns.  */}
          {/* 'offset-md-3' creates a horizontal space of three grid columns to the left of the column with the class 'col-md-6' */}
          <div className="card">
            <div className="card-header fs-3 text-center">Edit Product</div>
            {/* 'fs-3' used to define font sizes, 'text-center' class is used to horizontally center-align the content */}
            {message && (
              <p className="fs-4 text-center text-success">{message}</p>
            )}
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label>Enter Name</label>
                  <input
                    type="text"
                    name="name"
                    value={product.name} // Without this textbox will not become empty after adding the product
                    className="form-control"
                    onChange={handleChange}
                  />
                </div>
                <div className="mb-3">
                  <label>Enter Price</label>
                  <input
                    type="text"
                    name="price"
                    value={product.price}
                    className="form-control"
                    onChange={handleChange}
                  />
                </div>
                <div className="mb-3">
                  <label>Enter Quantity</label>
                  <input
                    type="text"
                    name="quantity"
                    value={product.quantity}
                    className="form-control"
                    onChange={handleChange}
                  />
                </div>
                <button className="btn btn-primary col-md-12">Submit</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EditProduct;
