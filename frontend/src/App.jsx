import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container, Box, Grid } from '@mui/material';
import PetsIcon from '@mui/icons-material/Pets';
import VaccinesIcon from '@mui/icons-material/Vaccines';
import RestaurantIcon from '@mui/icons-material/Restaurant';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

// Placeholder pages - will be implemented
const Home = () => (
  <Box sx={{ py: 4 }}>
    <Typography variant="h3" component="h1" gutterBottom align="center">
      Welcome to Pet Adoption & Care Tracker
    </Typography>
    <Typography variant="h6" align="center" color="text.secondary" paragraph>
      Find pets for adoption nearby, track vaccinations, feeding schedules, and vet appointments.
    </Typography>
    
    <Grid container spacing={4} sx={{ mt: 4 }}>
      <Grid item xs={12} sm={6} md={3}>
        <Box sx={{ textAlign: 'center', p: 3 }}>
          <PetsIcon sx={{ fontSize: 60, color: 'primary.main' }} />
          <Typography variant="h5" gutterBottom>
            Pet Adoption
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Browse and manage pets available for adoption
          </Typography>
        </Box>
      </Grid>
      <Grid item xs={12} sm={6} md={3}>
        <Box sx={{ textAlign: 'center', p: 3 }}>
          <VaccinesIcon sx={{ fontSize: 60, color: 'primary.main' }} />
          <Typography variant="h5" gutterBottom>
            Vaccinations
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Track vaccination history and reminders
          </Typography>
        </Box>
      </Grid>
      <Grid item xs={12} sm={6} md={3}>
        <Box sx={{ textAlign: 'center', p: 3 }}>
          <RestaurantIcon sx={{ fontSize: 60, color: 'primary.main' }} />
          <Typography variant="h5" gutterBottom>
            Feeding Schedules
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Manage feeding times and portions
          </Typography>
        </Box>
      </Grid>
      <Grid item xs={12} sm={6} md={3}>
        <Box sx={{ textAlign: 'center', p: 3 }}>
          <CalendarMonthIcon sx={{ fontSize: 60, color: 'primary.main' }} />
          <Typography variant="h5" gutterBottom>
            Vet Appointments
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Schedule and track vet visits
          </Typography>
        </Box>
      </Grid>
    </Grid>
  </Box>
);

function App() {
  return (
    <Router>
      <AppBar position="static">
        <Toolbar>
          <PetsIcon sx={{ mr: 2 }} />
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Pet Adoption & Care Tracker
          </Typography>
          <Button color="inherit" component={Link} to="/">Home</Button>
          <Button color="inherit" component={Link} to="/pets">Pets</Button>
          <Button color="inherit" component={Link} to="/vaccinations">Vaccinations</Button>
          <Button color="inherit" component={Link} to="/feeding">Feeding</Button>
          <Button color="inherit" component={Link} to="/appointments">Appointments</Button>
        </Toolbar>
      </AppBar>
      
      <Container maxWidth="lg">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/pets" element={<Home />} />
          <Route path="/vaccinations" element={<Home />} />
          <Route path="/feeding" element={<Home />} />
          <Route path="/appointments" element={<Home />} />
        </Routes>
      </Container>
    </Router>
  );
}

export default App;
