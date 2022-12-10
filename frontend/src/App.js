import { createTheme } from '@mui/material';
import { ThemeProvider } from '@mui/material/styles';
import Button from '@material-ui/core/Button';
import { useState } from 'react';
import SignUpDialog from './components/Modals/SignUpDialog';

function App() {

  const [alreadyRegistered, updateAuthType] = useState(true);
  const [open, setOpen] = useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  const theme = createTheme({});

  return (
    <ThemeProvider theme={theme}>
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
              <div className='App'>
              
              </div>
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
            <Button variant="contained" color="primary" onClick={handleOpen}>
                Signup
              </Button>
              <SignUpDialog open={open} handleClose={handleClose} />
          </div>
        </div>
      </div>
      </ThemeProvider>
  );
}

export default App;
