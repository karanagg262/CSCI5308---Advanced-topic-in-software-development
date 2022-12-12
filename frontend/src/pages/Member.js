import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../App.css";
import { context } from "../Store";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { regular, solid } from '@fortawesome/fontawesome-svg-core/import.macro';
import Menu from "../components/Menu";

function Member() {

    const navigate = useNavigate();
    const [state,] = useContext(context);
    let [open, setOpen] = useState(false);
    let [members, ] = useState([
        {
            name: 'John Doe',
            isAdmin: true
        },
        {
            name: 'Jane Doe',
            isAdmin: false
        },
        {
            name: 'Dave Smith',
            isAdmin: false
        }
    ]);

    const renderMembers = () => {
        const renderedMembers = members.map((item, index) => {
            return (
                <div className="checklist-item" key={index}>
                    <div className="checklist-item-name">
                        {item.name}
                    </div>
                    {
                        item.isAdmin ?
                            <></>
                            :
                            <div className="checklist-item-checkbox">
                                <FontAwesomeIcon icon={solid("trash")} className="trash-icon" />
                            </div>
                    }
                </div>
            );
        });
        return renderedMembers;
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
                    members.length === 0
                        ?
                        <div className="group-no-member">
                            Please add members.
                        </div>
                        :
                        <div style={{ overflowY: 'scroll', height: '65vh' }}>
                            {renderMembers()}
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

export default Member;
