import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../App.css";
import { context } from "../Store";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { regular, solid } from '@fortawesome/fontawesome-svg-core/import.macro';
import Menu from "../components/Menu";

function GroupDetail() {

    const currencySign = {
        'CAD': '$',
        'INR': 'Rs.',
    };

    const navigate = useNavigate();
    const [state,] = useContext(context);
    let [expenses,] = useState([
        {
            id: 1,
            transaction_id: 1,
            description: 'Lunch',
            amount: 20,
            currency: 'CAD',
            fromuserid: 'Jaimin',
            touserid: 'Taksh',
            fulllAmount: 100,
            groupid: state.group.id,
            date: '2 Dec'
        },
        {
            id: 2,
            transaction_id: 2,
            description: 'Dinner',
            amount: -20,
            currency: 'CAD',
            fromuserid: 'Taksh',
            touserid: 'Jaimin',
            fulllAmount: 83,
            groupid: state.group.id,
            date: '1 Dec'
        },
        {
            id: 1,
            transaction_id: 1,
            description: 'Lunch',
            amount: 20,
            currency: 'CAD',
            fromuserid: 'Jaimin',
            touserid: 'Taksh',
            fulllAmount: 100,
            groupid: state.group.id,
            date: '2 Dec'
        },
        {
            id: 2,
            transaction_id: 2,
            description: 'Dinner',
            amount: -20,
            currency: 'CAD',
            fromuserid: 'Taksh',
            touserid: 'Jaimin',
            fulllAmount: 83,
            groupid: state.group.id,
            date: '1 Dec'
        },
        {
            id: 1,
            transaction_id: 1,
            description: 'Lunch',
            amount: 20,
            currency: 'CAD',
            fromuserid: 'Jaimin',
            touserid: 'Taksh',
            fulllAmount: 100,
            groupid: state.group.id,
            date: '2 Dec'
        },
        {
            id: 2,
            transaction_id: 2,
            description: 'Dinner',
            amount: -20,
            currency: 'CAD',
            fromuserid: 'Taksh',
            touserid: 'Jaimin',
            fulllAmount: 83,
            groupid: state.group.id,
            date: '1 Dec'
        }
    ]);
    let [members,] = useState([]);
    let [open, setOpen] = useState(false);

    const renderExpenses = () => {
        const renderedExpenses = expenses.map((expense) => {
            return (
                <div className="expense">
                    <div className="expense-date">
                        {expense.date}
                    </div>
                    <div className="expense-name">
                        <div className="expense-description">
                            {expense.description}
                        </div>
                        <div className="expense-details">
                            {expense.fromuserid} paid {currencySign[expense.currency]}{expense.fulllAmount}
                        </div>
                    </div>
                    <div className={expense.amount > 0 ? "expense-amount-positive" : "expense-amount-negative"}>
                        {
                            expense.amount > 0
                                ?
                                <>
                                    {currencySign[expense.currency]}{expense.amount}
                                </>
                                :
                                <>
                                    {currencySign[expense.currency]}{expense.amount * -1}
                                </>
                        }
                    </div>
                </div>
            )
        });
        return renderedExpenses;
    }

    return (
        <div>
            <div className='page-header'>
                <FontAwesomeIcon icon={solid("bars")} className="header-menu-icon" onClick={() => setOpen(true)} />
                <div className="header-logo" onClick={() => {
                    navigate('/home');
                }}>
                    Triplify
                </div>
                <div className="header-options">
                    <div className="header-option">Friends</div>
                    <div className="header-option">Explore</div>
                    <div className="header-option">Posts</div>
                    <div className="header-option">
                        <FontAwesomeIcon icon={regular("user")} style={{ color: '#fff', fontSize: '15px' }} />
                    </div>
                </div>
            </div>
            <div className="group-header">
                <div className="group-title">
                    <div className="group-name">
                        {state.group.name}
                    </div>
                    <div className="group-destination">
                        {state.group.destination}
                    </div>
                </div>
                <div className="group-interval">
                    {state.group.startDate} - {state.group.endDate}
                </div>
            </div>
            <div>
                {
                    expenses.length === 0
                        ?
                        members.length === 0
                            ?
                            <div className="group-no-member">
                                Please add other group members.
                            </div>
                            :
                            <div className="group-no-member">
                                You don’t have any expenses right now.
                            </div>
                        :
                        <div style={{ overflowY: 'scroll', height: '65vh' }}>
                            {renderExpenses()}
                        </div>
                }
            </div>
            <div className="add-expense" onClick={() => navigate('/add-expense')}>
                <div className="add-expense-btn">
                    <div>
                        Settle Up
                    </div>
                </div>
                <div className="add-expense-btn">
                    <div onClick={() => navigate('add-expense')}>
                        Add Expense
                    </div>
                </div>
            </div>
            {
                open
                    ?
                    <Menu toggleOpen={setOpen} />
                    :
                    <></>
            }
        </div>
    );
}

export default GroupDetail;
