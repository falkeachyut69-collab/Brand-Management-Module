import { useEffect, useState } from "react";
import "./App.css";

const BASE_URL = "https://brand-management-module.onrender.com";

function App() {
  // ================= STATE =================
  const [groups, setGroups] = useState([]);
  const [chains, setChains] = useState([]);
  const [brands, setBrands] = useState([]);

  const [companyName, setCompanyName] = useState("");
  const [gstNumber, setGstNumber] = useState("");
  const [groupId, setGroupId] = useState("");

  const [brandName, setBrandName] = useState("");
  const [brandChainId, setBrandChainId] = useState("");

  const [editingChainId, setEditingChainId] = useState(null);
  const [editingBrandId, setEditingBrandId] = useState(null);

  const [view, setView] = useState("dashboard");

  // ================= LOAD DATA =================
  const loadGroups = () => {
    fetch(`${BASE_URL}/groups`)
      .then(res => res.json())
      .then(data => setGroups(data));
  };

  const loadChains = () => {
    fetch(`${BASE_URL}/chains`)
      .then(res => res.json())
      .then(data => setChains(data));
  };

  const loadBrands = () => {
    fetch(`${BASE_URL}/brands`)
      .then(res => res.json())
      .then(data => setBrands(Array.isArray(data) ? data : []));
  };

  useEffect(() => {
    loadGroups();
    loadChains();
    loadBrands();
  }, []);

  // ================= CHAIN =================
  const addChain = () => {
    if (!companyName || !gstNumber || !groupId) {
      alert("All fields required");
      return;
    }

    const url = editingChainId
      ? `${BASE_URL}/chains/${editingChainId}`
      : `${BASE_URL}/chains`;

    const method = editingChainId ? "PUT" : "POST";

    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        companyName,
        gstNumber,
        groupId: Number(groupId)
      })
    }).then(() => {
      resetChainForm();
      loadChains();
      setView("dashboard");
    });
  };

  const resetChainForm = () => {
    setCompanyName("");
    setGstNumber("");
    setGroupId("");
    setEditingChainId(null);
  };

  const editChain = (c) => {
    setCompanyName(c.companyName);
    setGstNumber(c.gstNumber);
    setGroupId(c.group?.groupId);
    setEditingChainId(c.chainId);
    setView("chainForm");
  };

  const deleteChain = (id) => {
    fetch(`${BASE_URL}/chains/${id}`, { method: "DELETE" })
      .then(() => loadChains());
  };

  // ================= BRAND =================
  const addBrand = () => {
    if (!brandName || !brandChainId) {
      alert("All fields required");
      return;
    }

    const url = editingBrandId
      ? `${BASE_URL}/brands/${editingBrandId}`
      : `${BASE_URL}/brands`;

    const method = editingBrandId ? "PUT" : "POST";

    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        brandName,
        chainId: Number(brandChainId)
      })
    }).then(() => {
      resetBrandForm();
      loadBrands();
      setView("dashboard");
    });
  };

  const resetBrandForm = () => {
    setBrandName("");
    setBrandChainId("");
    setEditingBrandId(null);
  };

  const editBrand = (b) => {
    setBrandName(b.brandName);
    setBrandChainId(b.chain?.chainId);
    setEditingBrandId(b.brandId);
    setView("brandForm");
  };

  const deleteBrand = (id) => {
    fetch(`${BASE_URL}/brands/${id}`, {
      method: "DELETE"
    }).then(() => loadBrands());
  };

  // ================= UI =================
  return (
    <div className="container">

      <h1>Brand Management Dashboard</h1>

      {/* NAV */}
      <div className="nav-buttons">
        <button onClick={() => setView("chainForm")}>Add Company</button>
        <button onClick={() => setView("brandForm")}>Add Brand</button>
        <button onClick={() => setView("dashboard")}>Dashboard</button>
      </div>

      {/* ================= CHAIN FORM ================= */}
      {view === "chainForm" && (
        <div className="card">
          <h2>{editingChainId ? "Update Company" : "Add Company"}</h2>

          <input
            placeholder="Company Name"
            value={companyName}
            onChange={(e) => setCompanyName(e.target.value)}
          />

          <input
            placeholder="GST Number"
            value={gstNumber}
            onChange={(e) => setGstNumber(e.target.value)}
          />

          <select
            value={groupId}
            onChange={(e) => setGroupId(e.target.value)}
          >
            <option value="">Select Group</option>
            {groups.map(g => (
              <option key={g.groupId} value={g.groupId}>
                {g.groupName}
              </option>
            ))}
          </select>

          <button onClick={addChain}>
            {editingChainId ? "Update" : "Add"}
          </button>
        </div>
      )}

      {/* ================= BRAND FORM ================= */}
      {view === "brandForm" && (
        <div className="card">
          <h2>{editingBrandId ? "Update Brand" : "Add Brand"}</h2>

          <input
            placeholder="Brand Name"
            value={brandName}
            onChange={(e) => setBrandName(e.target.value)}
          />

          <select
            value={brandChainId}
            onChange={(e) => setBrandChainId(e.target.value)}
          >
            <option value="">Select Company</option>
            {chains.map(c => (
              <option key={c.chainId} value={c.chainId}>
                {c.companyName}
              </option>
            ))}
          </select>

          <button onClick={addBrand}>
            {editingBrandId ? "Update" : "Add"}
          </button>
        </div>
      )}

      {/* ================= CHAINS TABLE ================= */}
      <div className="card">
        <h2>Companies</h2>

        <table className="table">
          <thead>
            <tr>
              <th>Name</th>
              <th>GST</th>
              <th>Group</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {chains.map(c => (
              <tr key={c.chainId}>
                <td>{c.companyName}</td>
                <td>{c.gstNumber}</td>
                <td>{c.group?.groupName}</td>
                <td className="actions">
                  <button onClick={() => editChain(c)}>Edit</button>
                  <button className="delete" onClick={() => deleteChain(c.chainId)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* ================= BRANDS TABLE ================= */}
      <div className="card">
        <h2>Brands</h2>

        <table className="table">
          <thead>
            <tr>
              <th>Brand</th>
              <th>Company</th>
              <th>Action</th>
            </tr>
          </thead>

          <tbody>
            {brands.map(b => (
              <tr key={b.brandId}>
                <td>{b.brandName}</td>
                <td>{b.chain?.companyName}</td>
                <td className="actions">
                  <button onClick={() => editBrand(b)}>Edit</button>
                  <button className="delete" onClick={() => deleteBrand(b.brandId)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

    </div>
  );
}

export default App;