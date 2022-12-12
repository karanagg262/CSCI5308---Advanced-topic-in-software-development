import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../App.css";
import { context } from "../Store";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { regular, solid } from '@fortawesome/fontawesome-svg-core/import.macro';
import Menu from "../components/Menu";

function Checklist() {

    const navigate = useNavigate();
    const [state,] = useContext(context);
    let [open, setOpen] = useState(false);
    let [checklist, updateChecklist] = useState([
        {
            name: 'Rain Coat',
            checked: true
        },
        {
            name: 'Trekking shoes',
            checked: false
        },
        {
            name: 'Backpack',
            checked: false
        },
        {
            name: 'SunScreen',
            checked: false
        },
        {
            name: 'Covid Checklist',
            checked: false
        }
    ]);

    const renderChecklist = () => {
        const renderedChecklist = checklist.map((item, index) => {
            return (
                <div className="checklist-item" key={index}>
                    <div className="checklist-item-name">
                        {item.name}
                    </div>
                    <div className="checklist-item-checkbox">
                        <input type="checkbox" checked={item.checked} onChange={() => {
                            let newChecklist = [...checklist];
                            newChecklist[index].checked = !newChecklist[index].checked;
                            updateChecklist(newChecklist);
                        }} />
                    </div>
                </div>
            );
        });
        return renderedChecklist;
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
                    checklist.length === 0
                        ?
                        <div className="group-no-member">
                            Please add checklists.
                        </div>
                        :
                        <div style={{ overflowY: 'scroll', height: '65vh' }}>
                            {renderChecklist()}
                        </div>
                }
            </div>
            <div className="home-add" onClick={() => navigate('/add-group')}>
                <div>
                    +
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

export default Checklist;
