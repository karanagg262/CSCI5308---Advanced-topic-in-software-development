import { useState } from 'react';
import './App.css';

function App() {

  const [alreadyRegistered, updateAuthType] = useState(true);

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
              <input className="first-name" placeholder='First Name' />
              <input className="last-name"  placeholder='Last Name' />
            </>
          }
          <br />
          <input className="email" placeholder='Email' />
          <br />
          <input className="password" placeholder='Password' />
          <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '15px', marginBottom: '30px', marginInline: '30px', alignItems: 'center' }}>
            <div className={alreadyRegistered ? 'login-btn-selected' : 'login-btn'}
              onClick={() => {
                if (!alreadyRegistered) {
                  updateAuthType(true);
                }
              }}
            >Login</div>
            <div className={alreadyRegistered ? 'register-btn' : 'register-btn-selected'}
              onClick={() => {
                if (alreadyRegistered) {
                  updateAuthType(false);
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
