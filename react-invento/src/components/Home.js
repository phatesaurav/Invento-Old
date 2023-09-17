import { useEffect, useState } from "react";
import base_url from "../api/BaseURL";
import axios from "axios";
import { Link } from "react-router-dom";

function Home() {
  const [productList, setProductList] = useState([]);
  const [message, setMessage] = useState("");

  function init() {
    axios.get(`${base_url}/getAllProducts`).then(
      function (response) {
        console.log(response.data);
        setProductList(response.data);
      },
      function (error) {
        console.log(error);
      }
    );
  }
  
  useEffect(function () {
    document.title = "Home";
    init();
  }, []);

  function deleteProduct(productId) {
    axios.delete(`${base_url}/deleteProduct/${productId}`).then(
      function (response) {
        init();
        console.log(`Deleted product with ID ${productId}`);
        setMessage("Product deleted successfully!");
        setTimeout(() => {
          setMessage("");
        }, 3000);
      },
      function (error) {
        console.log(error);
        setMessage("Error deleting product. Please try again.");
        setTimeout(() => {
          setMessage("");
        }, 3000);
      }
    );
  }

  return (
    <div className="container mt-3">
      <div className="row">
        <div className="col-md-12">
          <div className="card">
            <div className="card-header fs-3 text-center">
              All Product List
              {message && (
                <p className="fs-4 text-center text-success">{message}</p>
              )}
            </div>
            <div className="card-body">
              <table class="table table-striped table-hover">
                <thead class="table-warning">
                  <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody class="table-group-divider">
                  {productList.map(function (p, index) {
                    return (
                      <tr>
                        <td>{index + 1}</td>
                        <td>{p.name}</td>
                        <td>{p.price}</td>
                        <td>{p.quantity}</td>
                        <td>
                          <Link
                            to={`/editProduct/${p.id}`}
                            className="btn btn-sm btn-primary"
                          >
                            Edit
                          </Link>
                          <button
                            onClick={() => deleteProduct(p.id)}
                            className="btn btn-sm btn-danger ms-1"
                          >
                            Delete
                          </button>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
