import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../App.css";

function AddExpense() {

    let [amount, updateAmount] = useState("");
    let [description, updateDescription] = useState("");
    const navigate = useNavigate();

    return (
        <>
            <div className='page-header'>
                <div className="header-logo">
                    Triplify
                </div>
                <div className="header-options">
                    <div className="header-option">Friends</div>
                    <div className="header-option">Explore</div>
                    <div className="header-option">Posts</div>
                </div>
            </div>
            <div style={{ display: 'flex', justifyContent: 'center' }}>
                <div style={{ marginTop: '40px', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                    {/* <input className="add-group-name" placeholder='Group Name' value={name} onChange={e => updateName(e.target.value)} /> */}
                    {/* <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                        <input className="add-group-date" placeholder='Trip End Date' value={startDate} onChange={e => updateStartDate(e.target.value)} />
                        <input className="add-group-date" placeholder='Trip End Date' value={startDate} onChange={e => updateStartDate(e.target.value)} />
                    </div> */}
                    <input className="add-group-name" placeholder='Description' value={description} onChange={e => updateDescription(e.target.value)} />
                    <input className="add-group-name" placeholder='Amount' value={amount} onChange={e => updateAmount(e.target.value)} />
                    <div className="add-group-btn" onClick={() => navigate('/group-detail')}>
                        Submit
                    </div>
                </div>
            </div>
        </>
    );
}

export default AddExpense;
