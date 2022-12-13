import { useState } from 'react';
import './App.css';
import { URL } from './Constants';
import { useNavigate } from 'react-router-dom';

function App() {

  const [alreadyRegistered, updateAuthType] = useState(true);
  const [firstname, updateFirstName] = useState("");
  const [lastname, updateLastName] = useState("");
  const [email, updateEmail] = useState("");
  const [password, updatePassword] = useState("");
  let navigate = useNavigate();


  const onLogin = () => {
    console.log("Login");
    console.log(email);
    console.log(password);

    fetch(URL + 'users/login?emailAddress=' + email + '&password=' + password, {
      method: 'POST',

    })
      .then(response => response.json())
      .then(data => {
        console.log(data);
      })
  }

  const onRegister = () => {
    console.log("Register");
    console.log(firstname);
    console.log(lastname);
    console.log(email);
    console.log(password);

    navigate('/home');
    fetch(URL + 'users/register?firstname=' + firstname + '&lastname=' + lastname + '&emailAddress=' + email + '&password=' + password, {
      method: 'POST',
    })
      .then(response => response.data)
      .then(data => {
        console.log(data);
      })
  }

  return (
    <>
      <div className='page-header'>
        Triplify
      </div>
      <div className="login-page">
        <div className='login-body'>
          <div className='login-header'>Login</div>
          {alreadyRegistered
            ?
            null
            :
            <>
              <input className="first-name" placeholder='First Name' value={firstname} onChange={e => updateFirstName(e.target.value)} />
              <input className="last-name" placeholder='Last Name' value={lastname} onChange={e => updateLastName(e.target.value)} />
            </>
          }
          <br />
          <input className="email" placeholder='Email' value={email} onChange={e => updateEmail(e.target.value)} />
          <br />
          <input className="password" placeholder='Password' value={password} onChange={e => updatePassword(e.target.value)} />
          <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '15px', marginBottom: '30px', marginInline: '30px', alignItems: 'center' }}>
            <div className={alreadyRegistered ? 'login-btn-selected' : 'login-btn'}
              onClick={() => {
                if (!alreadyRegistered) {
                  updateAuthType(true);
                } else {
                  onLogin();
                }
              }}
            >Login</div>
            <div className={alreadyRegistered ? 'register-btn' : 'register-btn-selected'}
              onClick={() => {
                if (alreadyRegistered) {
                  updateAuthType(false);
                } else {
                  onRegister();
                }
              }}
            >Register</div>
          </div>
        </div>
      </div>
    </>
  );
}

export default App;
