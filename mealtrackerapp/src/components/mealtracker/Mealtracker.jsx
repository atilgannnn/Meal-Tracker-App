import React, { useState, useEffect } from 'react';
import Select from 'react-select';
import { Form, FormGroup, Input, Button } from 'reactstrap';
import { useNavigate } from 'react-router-dom';
import logo from '../../images/tm-logo.png';

export default function Mealtracker() {
  const [showForm, setShowForm] = useState(true);
  const [selectedRole, setSelectedRole] = useState(null);
  const [inputValueUsername, setInputValueUsername] = useState('');
  const [inputValueEmail, setInputValueEmail] = useState('');
  const [inputValuePassword, setInputValuePassword] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    if (isLoggedIn === 'true') {
      navigate('/desktop');
    }
  }, []);

  const handleClick = () => {
    const email = document.getElementById('exampleEmail').value;
    const password = document.getElementById('examplePassword').value;

    const existingUsers = JSON.parse(localStorage.getItem('users')) || [];
    const user = existingUsers.find(
      (u) => u.email === email && u.password === password
    );

    if (user) {
      localStorage.setItem('isLoggedIn', 'true');
      localStorage.setItem('currentUsername', user.username);
      navigate('/desktop');
    } else {
      alert('Invalid email or password');
    }
  };

  const handleSignup = () => {
    const existingUsers = JSON.parse(localStorage.getItem('users')) || [];
    const isEmailExist = existingUsers.some((u) => u.email === inputValueEmail);

    if (isEmailExist) {
      alert('This email is already registered. Please use a different email.');
      return;
    }

    const userData = {
      username: inputValueUsername,
      email: inputValueEmail,
      password: inputValuePassword,
      role: selectedRole.value,
    };

    const updatedUsers = [...existingUsers, userData];

    localStorage.setItem('users', JSON.stringify(updatedUsers));
    localStorage.setItem('currentUsername', inputValueUsername); // Burada username'i localStorage'a ekliyoruz

    navigate('/');
  };

  const toggleForm = () => {
    setShowForm(!showForm);
  };

  const roleOptions = [
    { value: 'User', label: 'User' },
    { value: 'Admin', label: 'Admin' },
  ];

  return (
    <div>
      <div className='img-div'>
        <img src={logo} alt='Logo' />
      </div>
      <div className={showForm ? 'signin' : 'signup'}>
        <Form className='form'>
          <FormGroup>
            <Input
              className='input'
              id='exampleEmail'
              name='email'
              placeholder='Email'
              type='email'
            />
          </FormGroup>
          <FormGroup>
            <Input
              className='input'
              id='examplePassword'
              name='password'
              placeholder='Password'
              type='password'
            />
          </FormGroup>
          <Button className='button' onClick={handleClick}>
            Sign in
          </Button>
          <Button className='button' onClick={toggleForm}>
            Sign up
          </Button>
        </Form>
      </div>
      <div className={showForm ? 'signup' : 'signin'}>
        <Form className='form'>
          <FormGroup>
            <Input
              className='input'
              id='username'
              name='username'
              placeholder='Username'
              type='username'
              value={inputValueUsername}
              onChange={(e) => setInputValueUsername(e.target.value)}
            />
          </FormGroup>
          <FormGroup>
            <Input
              className='input'
              id='exampleEmail'
              name='email'
              placeholder='Email'
              type='email'
              value={inputValueEmail}
              onChange={(e) => setInputValueEmail(e.target.value)}
            />
          </FormGroup>
          <FormGroup>
            <Input
              className='input'
              id='examplePassword'
              name='password'
              placeholder='Password'
              type='password'
              value={inputValuePassword}
              onChange={(e) => setInputValuePassword(e.target.value)}
            />
          </FormGroup>
          <FormGroup>
            <Select
              options={roleOptions}
              placeholder='Select Role'
              className='selector'
              id='exampleUser'
              onChange={(selectedOption) => setSelectedRole(selectedOption)}
            />
          </FormGroup>
          <Button className='button' type='submit' onClick={handleSignup}>
            Submit
          </Button>
          <Button className='button' onClick={toggleForm}>
            Cancel
          </Button>
        </Form>
      </div>
    </div>
  );
}
